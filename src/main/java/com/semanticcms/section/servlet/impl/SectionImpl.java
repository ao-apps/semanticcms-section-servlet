/*
 * semanticcms-section-servlet - Sections nested within SemanticCMS pages and elements in a Servlet environment.
 * Copyright (C) 2013, 2014, 2015, 2016, 2017, 2019  AO Industries, Inc.
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
package com.semanticcms.section.servlet.impl;

import com.aoindustries.encoding.MediaWriter;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import static com.aoindustries.encoding.TextInXhtmlEncoder.encodeTextInXhtml;
import com.aoindustries.io.buffer.BufferResult;
import com.semanticcms.core.model.ElementContext;
import com.semanticcms.core.model.NodeBodyWriter;
import com.semanticcms.core.model.Page;
import com.semanticcms.core.servlet.PageIndex;
import com.semanticcms.section.model.Aside;
import com.semanticcms.section.model.Nav;
import com.semanticcms.section.model.Section;
import com.semanticcms.section.model.SectioningContent;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.jsp.SkipPageException;

// TODO: Implement with https://www.w3.org/TR/wai-aria-1.1/#aria-label
final public class SectionImpl {

	public static void writeSectioningContent(
		Writer out,
		ElementContext context,
		SectioningContent sectioningContent,
		String htmlElement,
		PageIndex pageIndex
	) throws IOException, ServletException, SkipPageException {
		// If this is the first sectioning content in the page, write the table of contents
		Page page = sectioningContent.getPage();
		if(page != null) {
			List<SectioningContent> topLevelSectioningContents = page.findTopLevelElements(SectioningContent.class);
			if(!topLevelSectioningContents.isEmpty() && topLevelSectioningContents.get(0) == sectioningContent) {
				try {
					context.include(
						"/semanticcms-section-servlet/toc.inc.jspx",
						out,
						Collections.singletonMap("page", page)
					);
				} catch(IOException | ServletException | SkipPageException | RuntimeException e) {
					throw e;
				} catch(Exception e) {
					throw new ServletException(e);
				}
			}
		}
		// Count the sectioning level by finding all sectioning contents in the parent elements
		int sectioningLevel = 2; // <h1> is reserved for page titles
		com.semanticcms.core.model.Element parentElement = sectioningContent.getParentElement();
		while(parentElement != null) {
			if(parentElement instanceof SectioningContent) sectioningLevel++;
			parentElement = parentElement.getParentElement();
		}
		// Highest tag is <h6>
		if(sectioningLevel > 6) throw new IOException("Sectioning exceeded depth of h6 (including page as h1): sectioningLevel = " + sectioningLevel);

		out.write('<');
		out.write(htmlElement);
		out.write("><h");
		char sectioningLevelChar = (char)('0' + sectioningLevel);
		out.write(sectioningLevelChar);
		String id = sectioningContent.getId();
		if(id != null) {
			out.write(" id=\"");
			PageIndex.appendIdInPage(
				pageIndex,
				sectioningContent.getPage(),
				id,
				new MediaWriter(textInXhtmlAttributeEncoder, out)
			);
			out.write('"');
		}
		out.write('>');
		encodeTextInXhtml(sectioningContent.getLabel(), out);
		out.write("</h");
		out.write(sectioningLevelChar);
		out.write('>');
		BufferResult body = sectioningContent.getBody();
		if(body.getLength() > 0) {
			out.write("<div class=\"h");
			out.write(sectioningLevelChar);
			out.write("Content\">");
			body.writeTo(new NodeBodyWriter(sectioningContent, out, context));
			out.write("</div>");
		}
		out.write("</");
		out.write(htmlElement);
		out.write('>');
	}

	public static void writeAside(
		Writer out,
		ElementContext context,
		Aside aside,
		PageIndex pageIndex
	) throws IOException, ServletException, SkipPageException {
		writeSectioningContent(out, context, aside, "aside", pageIndex);
	}

	public static void writeNav(
		Writer out,
		ElementContext context,
		Nav nav,
		PageIndex pageIndex
	) throws IOException, ServletException, SkipPageException {
		writeSectioningContent(out, context, nav, "nav", pageIndex);
	}

	public static void writeSection(
		Writer out,
		ElementContext context,
		Section section,
		PageIndex pageIndex
	) throws IOException, ServletException, SkipPageException {
		writeSectioningContent(out, context, section, "section", pageIndex);
	}

	/**
	 * Make no instances.
	 */
	private SectionImpl() {
	}
}
