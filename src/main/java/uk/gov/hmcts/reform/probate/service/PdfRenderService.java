package uk.gov.hmcts.reform.probate.service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.attach.impl.DefaultTagWorkerFactory;
import com.itextpdf.html2pdf.exceptions.Html2PdfException;
import com.itextpdf.kernel.exceptions.PdfException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.probate.exception.PdfRenderException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.MessageFormat;

@Component
@Slf4j
public class PdfRenderService {
    /**
     * Renders an HTML input to a PDF document.
     *
     * @param html A valid HTML document to be rendered into a PDF
     * @return A byte[] representing the rendered PDF document
     * @throws PdfRenderException If any exception occurs within the rendering process
     */
    public byte[] renderHtml(final String html) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (
            final PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
            final PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        ) {
            pdfDocument.setTagged();

            final ConverterProperties converterProperties = new ConverterProperties()
                    .setTagWorkerFactory(new DefaultTagWorkerFactory());

            HtmlConverter.convertToPdf(html, pdfDocument, converterProperties);
        } catch (PdfException e) {
            final String errorMessage = MessageFormat.format(
                    "PdfException from PDF handling [{0}]",
                    e.getMessage());
            log.error(errorMessage);
            throw new PdfRenderException(errorMessage, e);
        } catch (Html2PdfException e) {
            final String errorMessage = MessageFormat.format(
                    "Html2PdfException from HTML to PDF handling [{0}]",
                    e.getMessage());
            log.error(errorMessage);
            throw new PdfRenderException(errorMessage, e);
        } catch (IOException e) {
            final String errorMessage = MessageFormat.format(
                    "IOException while rendering HTML to PDF: [{0}]",
                    e.getMessage());
            log.error(errorMessage);
            throw new PdfRenderException(errorMessage, e);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
