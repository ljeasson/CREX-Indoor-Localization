import java.io.*;
import java.net.*;

public class Server {

	public static void main(String[] args) throws IOException {
		
		String fileName;
		FileOutputStream fos;
		DataOutputStream outToFile;
		String lineFromClient;
		BufferedReader inFromClient;
		DataOutputStream  outToClient;
		Socket connectionSocket;
		
		// Create welcoming socket
		ServerSocket welcomeSocket = new ServerSocket(7890);

		
		while(true) {
			
			// Wait on welcoming socket accept() method for client contact to create and
			// return new socket connecting to client
			System.out.println("Server is ready to be connected");
			connectionSocket = welcomeSocket.accept();

			
			// Create input stream attached to client socket 
			inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

			
			// Create output stream attached to client socket
			outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			
			// Read file name from client
			fileName = inFromClient.readLine();

			
			try {
				// Create file by file name and output stream attached to file
				fos = new FileOutputStream(fileName);
				outToFile = new DataOutputStream(fos);
			} catch (Exception e) {
				// Any error happens
				System.out.println(e);
				connectionSocket.close();
				return;
			}
			
			// Read "line:"+line from client and write line to file until receive "done"
			// indicating file end
			while ((lineFromClient = inFromClient.readLine()) != null) {
				if (lineFromClient.equals("done")) break;
				outToFile.writeBytes(lineFromClient.substring("line:".length())+"\r\n");
			}
			
			// Close file and socket
			fos.close();
			outToFile.close();
			connectionSocket.close();


		}

	}

}
