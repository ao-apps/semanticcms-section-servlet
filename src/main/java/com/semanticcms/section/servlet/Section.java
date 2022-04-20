/*
 * semanticcms-section-servlet - Sections nested within SemanticCMS pages and elements in a Servlet environment.
 * Copyright (C) 2013, 2014, 2015, 2016, 2017, 2019, 2020, 2021, 2022  AO Industries, Inc.
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
 * along with semanticcms-section-servlet.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.semanticcms.section.servlet;

import com.aoapps.html.servlet.DocumentEE;
import com.semanticcms.core.model.ElementContext;
import com.semanticcms.core.servlet.PageContext;
import com.semanticcms.section.servlet.impl.SectionImpl;
import java.io.IOException;
import java.io.Writer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.SkipPageException;

/**
 * <a href="https://www.w3.org/TR/html5/sections.html#the-section-element">The section element</a>
 */
public class Section extends SectioningContent<com.semanticcms.section.model.Section> {

  public Section(
    ServletContext servletContext,
    HttpServletRequest request,
    HttpServletResponse response,
    com.semanticcms.section.model.Section element,
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

  public Section(
    ServletContext servletContext,
    HttpServletRequest request,
    HttpServletResponse response,
    String label
  ) {
    this(
      servletContext,
      request,
      response,
      new com.semanticcms.section.model.Section(),
      label
    );
  }

  /**
   * Creates a new section in the current page context.
   *
   * @see  PageContext
   */
  public Section(
    com.semanticcms.section.model.Section element,
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

  @Override
  public void writeTo(Writer out, ElementContext context) throws IOException, ServletException, SkipPageException {
    SectionImpl.writeSection(
      request,
      new DocumentEE(servletContext, request, response, out),
      context,
      element,
      pageIndex
    );
  }
}
