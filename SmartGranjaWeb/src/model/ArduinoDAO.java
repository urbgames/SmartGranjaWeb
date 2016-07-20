package model;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Observable;

public class ArduinoDAO extends Observable implements SerialPortEventListener {

	private OutputStream serialOut;
	private String portaCOM;
	private int taxa;
	private BufferedReader input;
	private SerialPort serialPort;
	private String inputLine;

	public ArduinoDAO(String portaCOM, int taxa) {
		this.portaCOM = portaCOM;
		this.taxa = taxa;
		this.initialize();
	}

	public void initialize() {
		try {
			CommPortIdentifier portaId = null;
			try {
				portaId = CommPortIdentifier.getPortIdentifier(portaCOM);
			} catch (Exception e) {
				e.printStackTrace();
			}
			serialPort = (SerialPort) portaId.open("Comunica��o", taxa);
			serialPort.setSerialPortParams(this.taxa, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			serialOut = serialPort.getOutputStream();
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			serialPort.removeEventListener();
			serialPort.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enviarDados(int opcao) {
		try {
			serialOut.write(opcao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				inputLine = input.readLine();
				setChanged();
				notifyObservers();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getInputLine() {
		return inputLine;
	}

	public void setInputLine(String inputLine) {
		this.inputLine = inputLine;
	}

}