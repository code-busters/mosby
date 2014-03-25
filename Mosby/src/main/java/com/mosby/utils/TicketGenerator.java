package main.java.com.mosby.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Date;

import main.java.com.mosby.model.Ticket;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

public class TicketGenerator {

	private static String EVENT_LOGO_PATH = "media\\images\\events\\logo";

	private Document document;

	private String eventName;

	private String eventCategory;
	private String eventType;

	private Date startDate;
	private Date startTime;
	private Date endDate;
	private Date endTime;

	private String location;
	private String ticketType;

	private String logoURL;
	private String personName;
	private String qrCode;
	private String promoCode;

	private Date purchaseTime;

	public TicketGenerator() {

	}

	public TicketGenerator(Ticket ticket, OutputStream outputStream)
			throws FileNotFoundException, DocumentException {
		super();
		this.document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, outputStream);
		this.eventName = ticket.getEvent().getName();
		this.eventCategory = ticket.getEvent().getEventCategory().getCategory();
		this.eventType = ticket.getEvent().getEventType().getType();
		this.startDate = ticket.getEvent().getStartDate();
		this.endDate = ticket.getEvent().getEndDate();
		this.startTime = ticket.getEvent().getStartTime();
		this.endTime = ticket.getEvent().getEndTime();
		this.location = ticket.getEvent().getLocation();
		this.ticketType = ticket.getTicketInfo().getType();
		this.logoURL = ticket.getEvent().getLogo();
		this.personName = ticket.getUser().getFirstName()
				+ ticket.getUser().getLastName();
		this.qrCode = "temp QR-code";
		this.promoCode = ticket.getPromoCode().getCode();
		this.purchaseTime = ticket.getTimeOfPurchase();
	}

	public void generateTicket() throws DocumentException,
			MalformedURLException, IOException {
		document.open();

		document.add(headerTable());

		Font namesFont = new Font(FontFamily.HELVETICA, 8f, 3, BaseColor.GRAY);
		Font headerFont = new Font(FontFamily.HELVETICA, 19f, 1,
				BaseColor.BLACK);
		Font textFont = new Font(FontFamily.HELVETICA, 13f, 0, BaseColor.BLACK);

		PdfPTable table = new PdfPTable(3);
		table.setWidths(new int[] { 1, 6, 2 });
		table.setWidthPercentage(100);

		PdfPTable nestedTable = new PdfPTable(1);
		nestedTable.setWidthPercentage(100);

		PdfPCell nestedCell = generateCell();
		nestedCell.setRotation(90);
		nestedCell.setFixedHeight(300);
		nestedTable.addCell(nestedCell);
		PdfPCell leftColumn = new PdfPCell();
		leftColumn.addElement(nestedTable);
		table.addCell(leftColumn);

		nestedTable = new PdfPTable(1);
		nestedTable.setWidthPercentage(100);

		nestedCell = generateCell("Event", eventName, namesFont, headerFont, 60);
		nestedTable.addCell(nestedCell);
		nestedCell = generateCell("Type", eventType, namesFont, textFont, 45);
		nestedTable.addCell(nestedCell);
		nestedCell = generateCell("Category", eventCategory, namesFont,
				textFont, 45);
		nestedTable.addCell(nestedCell);
		nestedCell = generateCell("Date & Time", "From " + startDate.toString()
				+ " To " + endDate.toString() + " : " + startTime.toString()
				+ " - " + endTime.toString(), namesFont, textFont, 50);
		nestedTable.addCell(nestedCell);
		nestedCell = generateCell("Location", location, namesFont, textFont, 50);
		nestedTable.addCell(nestedCell);
		nestedCell = generateCell("Time of purchase", purchaseTime.toString(),
				namesFont, textFont, 50);
		nestedTable.addCell(nestedCell);

		PdfPCell centerColumn = new PdfPCell();
		centerColumn.addElement(nestedTable);
		table.addCell(centerColumn);

		nestedTable = new PdfPTable(1);
		nestedTable.setWidthPercentage(100);

		nestedCell = generateImageCell(logoURL);
		nestedTable.addCell(nestedCell);
		nestedCell = generateCell("Name", personName, namesFont, textFont, 40);
		nestedTable.addCell(nestedCell);
		nestedCell = generateCell("Ticket type", ticketType, namesFont,
				textFont, 40);
		nestedTable.addCell(nestedCell);
		nestedCell = generateCell("Promo-code", promoCode, namesFont, textFont,
				40);
		nestedTable.addCell(nestedCell);
		nestedCell = generateQrCodeCell(qrCode);
		nestedTable.addCell(nestedCell);

		PdfPCell rightColumn = new PdfPCell();
		rightColumn.addElement(nestedTable);
		table.addCell(rightColumn);
		table.setSpacingAfter(30);

		document.add(table);

		document.add(generateDottedLine());

		document.close();
		System.out.println("END");
	}

	private Chunk generateDottedLine() {
		Chunk dottedLine;

		DottedLineSeparator separator = new DottedLineSeparator();
		separator.setLineWidth(1.5f);
		separator.setPercentage(55550f / 182f);
		dottedLine = new Chunk(separator);

		return dottedLine;
	}

	private PdfPCell generateCell() {
		PdfPCell cell;

		Font font = new Font(FontFamily.HELVETICA, 20f, 0, BaseColor.BLACK);
		Phrase phrase = new Phrase("inspired by MOSBY.", font);
		cell = new PdfPCell(phrase);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(4);
		cell.setPadding(10f);
		cell.setBorderColor(BaseColor.LIGHT_GRAY);

		return cell;
	}

	private PdfPCell generateCell(String cellName, String cellValue,
			Font namesFont, Font textFont, float height) {
		PdfPCell cell;

		Chunk name = new Chunk(cellName + "\n\n", namesFont);
		Chunk value = new Chunk(cellValue + "\n\n", textFont);
		Phrase phrase = new Phrase();
		phrase.add(name);
		phrase.add(value);
		cell = new PdfPCell(phrase);
		cell.setBorderWidth(4);
		cell.setPadding(5f);
		cell.setFixedHeight(height);
		cell.setBorderColor(BaseColor.LIGHT_GRAY);

		return cell;
	}

	private PdfPCell generateImageCell(String logoPath)
			throws BadElementException, MalformedURLException, IOException {
		PdfPCell cell;

		Image image;
		if (logoPath != null) {
			image = Image.getInstance("E:\\�����\\������\\workspaceEE\\Mosby\\WebContent\\media\\images\\events\\logo\\default.png");
		} else {
			image = Image.getInstance(EVENT_LOGO_PATH + "\\default.png");
		}
		image.scaleToFit(90, 90);
		image.setBorder(Image.BOX);
		image.setBorderWidth(10);
		image.setBorderColor(BaseColor.BLACK);
		cell = new PdfPCell(image);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setBorderWidth(4);
		cell.setPadding(5f);
		cell.setBorderColor(BaseColor.LIGHT_GRAY);

		return cell;
	}

	private PdfPCell generateQrCodeCell(String code)
			throws BadElementException, MalformedURLException, IOException {
		PdfPCell cell;

		BarcodeQRCode qrCode = new BarcodeQRCode(code, 1, 1, null);
		Image image = qrCode.getImage();
		image.scaleToFit(70, 70);
		cell = new PdfPCell(image);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(4);
		cell.setPadding(5f);
		cell.setBorderColor(BaseColor.LIGHT_GRAY);

		return cell;
	}

	private PdfPTable headerTable() {
		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(50);

		Font font = new Font(FontFamily.TIMES_ROMAN, 14f, 0, BaseColor.WHITE);
		PdfPCell cell = new PdfPCell(new Phrase("Please, print this", font));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(BaseColor.BLACK);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setFixedHeight(30);
		headerTable.addCell(cell);
		headerTable.setSpacingAfter(20);

		return headerTable;
	}

}