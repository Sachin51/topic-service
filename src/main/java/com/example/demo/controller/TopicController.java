package com.example.demo.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Topic;
import com.example.demo.service.TopicService;

//@CrossOrigin(origins = "http://localhost:4100", maxAge = 3600)
@RestController
@RequestMapping(value = "/topic")
public class TopicController {

	private static Logger LOGGER = Logger.getLogger(TopicController.class);

	@Autowired
	TopicService topicService;

	@GetMapping(value = "/listTopics")
	public List<Topic> getAllTopics(@RequestParam int pageNumber, @RequestParam int numberOfResults) {
		LOGGER.debug("Controller Fetching topics----------------------------------------------");
		return topicService.getAllTopics(pageNumber, numberOfResults);
	}

	@GetMapping(value = "/getTopic/{topicId}")
	public Optional<Topic> getAllTopics(@PathVariable int topicId) {
		LOGGER.debug("Controller Fetching topics----------------------------------------------");
		return topicService.getTopic(topicId);
	}

	@GetMapping(value = "/base64Encoding")
	public void base64Encoding() {

		String str1 = "My Password";
		byte[] encodedPassword = Base64.encodeBase64(str1.getBytes());
		System.out.println("Encoded password: " + encodedPassword);

		byte[] decoded = Base64.decodeBase64(encodedPassword);
		System.out.println("Decoded password: " + new String(encodedPassword));
	}

	@GetMapping(value = "/hello")
	public String greet() {
		LOGGER.debug("Controller Fetching topics----------------------------------------------");
		return "Hello....!!!";
	}

	@PostMapping(value = "/addTopic")
	public String addTopic(@RequestBody Topic topic) {
		LOGGER.debug("Adding Topic----------------------------------------------");
		return topicService.addTopicDemo(topic);
	}

	@GetMapping(value = "/readPdfAndPrintContent")
	public void printPdfFileContents() {
		// Read this for more related to file related functionalities
		// https://www.baeldung.com/java-write-to-file
		// PrintWriter is used to write formatted text, FileOutputStream to write binary
		// data,
		// DataOutputStream to write primitive data types, RandomAccessFile to write to
		// a specific position,
		// and FileChannel to write faster in larger files.
		String pdfFileInText = null;
		try (PDDocument document = PDDocument
				.load(new File("C:\\Users\\sannigeri\\Downloads\\SachinKumar_JavaFullStackProfessional_Resume.pdf"))) {
			// document.getClass();
			if (!document.isEncrypted()) {
				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);

				PDFTextStripper tStripper = new PDFTextStripper();
				pdfFileInText = tStripper.getText(document);

				System.out.println(pdfFileInText);

				String lines[] = pdfFileInText.split("\\r?\\n");
				for (String line : lines) {
					System.out.println(line);
				}

			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\sannigeri\\Desktop\\test.txt"));
			writer.write(pdfFileInText);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			FileWriter fileWriter = new FileWriter("C:\\Users\\sannigeri\\Desktop\\testFormatted.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.print("Some String");
			printWriter.printf("Product name is %s and its price is %d", "Iphone", 1000);
			printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String str = "Hello all..!!";
		byte[] strToBytes = str.getBytes();
		try {
			FileOutputStream outputStream = new FileOutputStream("C:\\Users\\sannigeri\\Desktop\\testBinary.txt");
			System.out.println("Bytes: " + strToBytes);
			outputStream.write(strToBytes);
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long position = 5;
		int data = 7879;
		int result;
		try {
			RandomAccessFile writer = new RandomAccessFile("C:\\Users\\sannigeri\\Desktop\\testFormatted.txt", "rw");
			writer.seek(position);
			writer.write(data);
			// writer.close();

			writer.seek(position);
			result = writer.readInt();
			writer.close();
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
