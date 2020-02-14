/*
 * semanticcms-section-servlet - Sections nested within SemanticCMS pages and elements in a Servlet environment.
 * Copyright (C) 2019, 2020  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of semanticcms-section-servlet.
 *
 * semanticcms-section-servlet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * semanticcms-section-servlet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with semanticcms-section-servlet.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.semanticcms.section.servlet;

import com.aoindustries.html.servlet.HtmlEE;
import com.semanticcms.core.model.ElementContext;
import com.semanticcms.core.pages.local.PageContext;
import com.semanticcms.section.renderer.html.SectionHtmlRenderer;
import java.io.IOException;
import java.io.Writer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.SkipPageException;

/**
 * <a href="https://www.w3.org/TR/html5/sections.html#the-nav-element">The nav element</a>
 */
public class Nav extends SectioningContent<com.semanticcms.section.model.Nav> {

	public Nav(
		ServletContext servletContext,
		HttpServletRequest request,
		HttpServletResponse response,
		com.semanticcms.section.model.Nav element,
		String label
	) {
		super(
			servletContext,
			request,
			response,
			element,
			label
		);
	}

	public Nav(
		ServletContext servletContext,
		HttpServletRequest request,
		HttpServletResponse response,
		String label
	) {
		this(
			servletContext,
			request,
			response,
			new com.semanticcms.section.model.Nav(),
			label
		);
	}

	/**
	 * Creates a new nav in the current page context.
	 *
	 * @see  PageContext
	 */
	public Nav(
		com.semanticcms.section.model.Nav element,
		String label
	) {
		this(
			PageContext.getServletContext(),
			PageContext.getRequest(),
			PageContext.getResponse(),
			element,
			label
		);
	}

	/**
	 * Creates a new nav in the current page context.
	 *
	 * @see  PageContext
	 */
	public Nav(String label) {
		this(
			PageContext.getServletContext(),
			PageContext.getRequest(),
			PageContext.getResponse(),
			label
		);
	}

	@Override
	public Nav id(String id) {
		super.id(id);
		return this;
	}

	@Override
	public void writeTo(Writer out, ElementContext context) throws IOException, ServletException, SkipPageException {
		SectionHtmlRenderer.writeNav(
			HtmlEE.get(servletContext, request, out),
			context,
			element,
			pageIndex
		);
	}
}
