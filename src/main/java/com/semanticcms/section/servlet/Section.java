/*
 * semanticcms-section-servlet - Sections nested within SemanticCMS pages and elements in a Servlet environment.
 * Copyright (C) 2013, 2014, 2015, 2016  AO Industries, Inc.
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

import com.semanticcms.core.model.ElementContext;
import com.semanticcms.core.servlet.CaptureLevel;
import com.semanticcms.core.servlet.Element;
import com.semanticcms.core.servlet.PageContext;
import com.semanticcms.core.servlet.PageIndex;
import com.semanticcms.section.servlet.impl.SectionImpl;
import java.io.IOException;
import java.io.Writer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.SkipPageException;

public class Section extends Element<com.semanticcms.section.model.Section> {

	public Section(
		ServletContext servletContext,
		HttpServletRequest request,
		HttpServletResponse response,
		String label
	) {
		super(
			servletContext,
			request,
			response,
			new com.semanticcms.section.model.Section()
		);
		element.setLabel(label);
	}

	/**
	 * Creates a new section in the current page context.
	 *
	 * @see  PageContext
	 */
	public Section(String label) {
		this(
			PageContext.getServletContext(),
			PageContext.getRequest(),
			PageContext.getResponse(),
			label
		);
	}

	@Override
	public Section id(String id) {
		super.id(id);
		return this;
	}

	private PageIndex pageIndex;
	@Override
	protected void doBody(CaptureLevel captureLevel, Body<? super com.semanticcms.section.model.Section> body) throws ServletException, IOException, SkipPageException {
		pageIndex = PageIndex.getCurrentPageIndex(request);
		super.doBody(captureLevel, body);
	}

	@Override
	public void writeTo(Writer out, ElementContext context) throws IOException {
		SectionImpl.writeSection(out, context, element, pageIndex);
	}
}
