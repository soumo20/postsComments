package fr.postscomments.pdf.models;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import fr.postscomments.posts.models.Post;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class PdfGenerator {

    public void generatorPdf(Post post, HttpServletResponse response) {
        try {
            // Creating the Object of Document
            Document document = new Document(PageSize.A4);
            // Getting instance of PdfWriter
            PdfWriter.getInstance(document, response.getOutputStream());
            // Opening the created document to change it
            document.open();
            // Creating font
            // Setting font style and size
            Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            fontTiltle.setSize(20);
            Font fontParagraph = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            fontParagraph.setSize(14);
            // Creating paragraph
            Paragraph paragraph1 = new Paragraph(post.getTitle(), fontTiltle);
            // Aligning the paragraph in the document
            paragraph1.setAlignment(Element.ALIGN_CENTER);
            // Adding the created paragraph in the document
            document.add(paragraph1);
            // Creating a second paragraph
            Paragraph paragraph2 = new Paragraph(post.getContent(), fontParagraph);
            paragraph2.setAlignment(Element.ALIGN_JUSTIFIED);
            // Adding the created paragraph in the document
            document.add(paragraph2);
            // Closing the document
            document.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new IllegalArgumentException("Problem in creation PDF");
        }
    }
}
