package uk.gov.hmcts.reform.probate.service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.exceptions.Html2PdfException;
import com.itextpdf.kernel.exceptions.PdfException;
import com.itextpdf.kernel.pdf.PdfDocument;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import uk.gov.hmcts.reform.probate.exception.PdfRenderException;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class PdfRenderServiceTest {
    PdfRenderService pdfRenderService = new PdfRenderService();

    final static private String INPUT = "";

    @Test
    void shouldCallHtmlConverter() {
        try (MockedStatic<HtmlConverter> htmlConverter = Mockito.mockStatic(HtmlConverter.class)) {
            final byte[] result = pdfRenderService.renderHtml(INPUT);

            htmlConverter.verify(() -> HtmlConverter.convertToPdf(eq(INPUT), any(PdfDocument.class), any()));
        }
    }

    @Test
    void shouldThrowPdfRenderException_whenPdfExceptionIsThrown() {
        final String exMessage = RandomStringUtils.random(8,0, 0,true, false, null, new Random());
        final PdfException pdfException = new PdfException(exMessage);
        // yuck
        try (
                MockedConstruction<PdfDocument> ignored = Mockito.mockConstruction(
                        PdfDocument.class,
                        (document, context) -> when(document.setTagged()).thenThrow(pdfException))
        ) {

            final PdfRenderException thrown =  assertThrows(
                    PdfRenderException.class,
                    () -> pdfRenderService.renderHtml(INPUT));

            assertTrue(thrown.getMessage().contains(exMessage));
            assertTrue(thrown.getMessage().startsWith("PdfException"));
            assertSame(pdfException, thrown.getCause());
        }
    }

    @Test
    void shouldThrowPdfRenderException_whenHtml2PdfExceptionIsThrown() {
        final String exMessage = RandomStringUtils.random(8,0, 0,true, false, null, new Random());
        final Html2PdfException html2PdfException = new Html2PdfException(exMessage);
        try (MockedStatic<HtmlConverter> htmlConverter = Mockito.mockStatic(HtmlConverter.class)) {
            htmlConverter.when(() -> HtmlConverter.convertToPdf(anyString(), any(PdfDocument.class), any()))
                    .thenThrow(html2PdfException);

            final PdfRenderException thrown =  assertThrows(
                    PdfRenderException.class,
                    () -> pdfRenderService.renderHtml(INPUT));

            assertTrue(thrown.getMessage().contains(exMessage));
            assertTrue(thrown.getMessage().startsWith("Html2PdfException"));
            assertSame(html2PdfException, thrown.getCause());
        }
    }
}
