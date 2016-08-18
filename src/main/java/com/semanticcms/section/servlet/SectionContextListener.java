/*
 * semanticcms-section-servlet - Sections nested within SemanticCMS pages and elements in a Servlet environment.
 * Copyright (C) 2016  AO Industries, Inc.
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

import com.semanticcms.core.servlet.SemanticCMS;
import com.semanticcms.section.servlet.impl.SectionImpl;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("Registers the \"" + SectionImpl.CSS_LINK + "\" CSS link in SemanticCMS.")
public class SectionContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		SemanticCMS.getInstance(event.getServletContext()).addCssLink(SectionImpl.CSS_LINK);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// Do nothing
	}
}
