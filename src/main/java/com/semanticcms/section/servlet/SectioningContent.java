/*
 * semanticcms-section-servlet - Sections nested within SemanticCMS pages and elements in a Servlet environment.
 * Copyright (C) 2019, 2021, 2022, 2024  AO Industries, Inc.
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

import com.semanticcms.core.servlet.CaptureLevel;
import com.semanticcms.core.servlet.Element;
import com.semanticcms.core.servlet.PageIndex;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.SkipPageException;

/**
 * <a href="https://www.w3.org/TR/html5/sections.html#the-section-element">The section element</a>.
 */
public abstract class SectioningContent<C extends com.semanticcms.section.model.SectioningContent> extends Element<C> {

  /**
   * Creates a new {@link SectioningContent}.
   */
  protected SectioningContent(
      ServletContext servletContext,
      HttpServletRequest request,
      HttpServletResponse response,
      C element,
      String label
  ) {
    super(
        servletContext,
        request,
        response,
        element
    );
    element.setLabel(label);
  }

  @Override
  public SectioningContent<C> id(String id) {
    super.id(id);
    return this;
  }

  protected PageIndex pageIndex;

  @Override
  protected void doBody(CaptureLevel captureLevel, Body<? super C> body) throws ServletException, IOException, SkipPageException {
    pageIndex = PageIndex.getCurrentPageIndex(request);
    super.doBody(captureLevel, body);
  }
}
