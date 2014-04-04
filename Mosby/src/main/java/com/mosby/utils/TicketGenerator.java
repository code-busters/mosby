package main.java.com.mosby.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import main.java.com.mosby.model.Ticket;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Date;

public class TicketGenerator {

	private static final String LOGO_PATH = "\\media\\images\\events\\logo\\";
	private static final int QR_SEED = 9;
	private static final int QR_SIZE = 10;

	private Document document;

	private String eventName;

	private String fullPath;
	
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

	public TicketGenerator(OutputStream outputStream, String path)
			throws FileNotFoundException, DocumentException {
		super();
		this.document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, outputStream);
		this.fullPath = StringUtils.concat(path, LOGO_PATH);
	}

	private void ticketData (Ticket ticket) {
		
		this.eventName = ticket.getEvent().getName();
		this.eventCategory = ticket.getEvent().getEventCategory().getCategory();
		this.eventType = ticket.getEvent().getEventType().getType();
		this.startDate = ticket.getEvent().getStartDate();
		this.endDate = ticket.getEvent().getEndDate();
		this.startTime = ticket.getEvent().getStartTime();
		this.endTime = ticket.getEvent().getEndTime();
		this.logoURL = StringUtils.concat(fullPath, ticket.getEvent().getLogo());
		this.personName = StringUtils.concat(ticket.getUser().getFirstName(), "\n", ticket.getUser().getLastName());
		
		if (!ticket.getEvent().getLocation().equals("")) {
			this.location = ticket.getEvent().getLocation();
		} else {
			this.location = "";
		}
		
		this.ticketType = ticket.getTicketInfo().getType();
		this.qrCode = qrCodeString(ticket.getId());
		this.purchaseTime = ticket.getTimeOfPurchase();
		
		if (ticket.getPromoCode() != null) {
			this.promoCode = ticket.getPromoCode().getCode();
		} else {
			this.promoCode = "none";
		}
	}
	
	public void createTickets(Ticket...tickets) {
		document.open();
		try {
			document.add(headerTable());
			generateTicket(tickets);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		} 
		
		document.close();
	}
	
	public void generateTicket(Ticket...tickets) throws DocumentException, IOException {

		for (int i = 0; i < tickets.length; i++) {
		System.out.println("STARTING GENERATION TICKET...");
		ticketData(tickets[i]);

		Font namesFont = new Font(FontFamily.HELVETICA, 8f, 3, BaseColor.GRAY);
		Font headerFont = new Font(FontFamily.HELVETICA, 17f, 1,
				BaseColor.BLACK);
		Font textFont = new Font(FontFamily.HELVETICA, 13f, 0, BaseColor.BLACK);

		PdfPTable table = new PdfPTable(3);
		table.setWidths(new int[] { 1, 6, 2 });
		table.setWidthPercentage(100);

		PdfPTable nestedTable = new PdfPTable(1);
		nestedTable.setWidthPercentage(100);

		PdfPCell nestedCell = generateCell();
		nestedCell.setRotation(90);
		nestedCell.setFixedHeight(305);
		nestedTable.addCell(nestedCell);
		PdfPCell leftColumn = new PdfPCell();
		leftColumn.addElement(nestedTable);
		table.addCell(leftColumn);

		nestedTable = new PdfPTable(1);
		nestedTable.setWidthPercentage(100);

		nestedCell = generateCell("Event", eventName, namesFont, headerFont, 65);
		nestedTable.addCell(nestedCell);
		nestedCell = generateCell("Type", eventType, namesFont, textFont, 45);
		nestedTable.addCell(nestedCell);
		nestedCell = generateCell("Category", eventCategory, namesFont,
				textFont, 45);
		nestedTable.addCell(nestedCell);
		nestedCell = generateCell("Date & Time", "From " + startDate.toString()
				+ " To " + endDate.toString() + " / " + startTime.toString()
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
		nestedCell = generateCell("Name", personName, namesFont, textFont, 60);
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

		Chunk dottedLine = generateDottedLine();
		
		document.add(dottedLine);
		Paragraph lastParagraph = new Paragraph();
		lastParagraph.setSpacingAfter(30);
		document.add(lastParagraph);
		System.out.println("ENDING GENERATION TICKET...");
	}
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
			throws BadElementException, IOException {
		PdfPCell cell;

		Image image = Image.getInstance(logoPath);
		
		image.scaleToFit(100, 100);
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
			throws BadElementException, IOException {
		PdfPCell cell;

		BarcodeQRCode qrCode = new BarcodeQRCode(code, 1, 1, null);
		Image image = qrCode.getImage();
		image.scaleToFit(75, 75);
		cell = new PdfPCell(image);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorderWidth(4);
		cell.setPadding(4.7f);
		cell.setBorderColor(BaseColor.LIGHT_GRAY);

		return cell;
	}
	
	public String qrCodeString(int ticketId) {
		String qrCode = Integer.toString(ticketId);
		int numberSum = 0;

        for (int i = 0; i < qrCode.length(); i++) {
			numberSum += Integer.parseInt(Character.toString(qrCode.charAt(i)));
		}
		int residual = (Math.abs(numberSum - QR_SEED)) % 10;
		while (qrCode.length() < QR_SIZE - 1) {
			qrCode = StringUtils.concat(0, qrCode); 
		}
		qrCode = StringUtils.concat(qrCode, residual);
		
		return qrCode;
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
