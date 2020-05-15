package com.netcracker.curs.fapi.steganography;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;

public class SteganographyImpl {

    private String str1 = "",str2 = "",strr="";
    private byte[] hiddenBytes;
    private byte[] encryptedMessage;
    private byte[] encryptedMsg;
    private File encryptedFile;

    public static String bytesToString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            sb.append((char) (bytes[i] + 128));
        }
        return sb.toString();
    }

    public static byte[] stringToBytes(String str) {
        int length = str.length();
        byte[] b = new byte[length];
        for (int i = 0; i < length; i++) {
            b[i] = (byte) (str.charAt(i) - 128);
        }
        return b;
    }

    public void hideMessage(byte[] msg, File imgFile) {
        BufferedImage im = null;
        File encryptedFile = null;
        try {
            im = ImageIO.read(imgFile);
            WritableRaster raster = im.getRaster();
            DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();

            byte[] writableBytes = buffer.getData();


            System.out.println("msgBytes : "+msg);

            int header = msg.length;
            byte[] lenBytes = intToBytes(header, 4);
            int totalLen = 4 + msg.length;
            byte[] bytesToHide = new byte[totalLen];

            System.arraycopy(lenBytes, 0, bytesToHide, 0, lenBytes.length);
            System.arraycopy(msg, 0, bytesToHide, lenBytes.length,
                    msg.length);

            if (bytesToHide.length * 8 > writableBytes.length) {
                throw new RuntimeException("Image too small to hide message");
            }

            System.out.println("Writing bytes:");
            int offset = 0;
            for (int i = 0; i < bytesToHide.length; i += 1) {
                byte b = bytesToHide[i];
                System.out.print(b);
                System.out.print(' ');
                for (int j = 0; j < 8; j += 1) {
                    int bit = (b >> j) & 1;
                    writableBytes[offset] = (byte) ((writableBytes[offset] & 0xFE) | bit);
                    offset += 1;
                }
            }
            System.out.println();
            System.out.println("Message length: " + msg.length);
            ImageIO.write(im, "png", imgFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public byte[] showMessage(File imgFile) {
        BufferedImage im = null;
        try {
            im = ImageIO.read(imgFile);
            WritableRaster raster = im.getRaster();
            DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
            byte[] data = buffer.getData();

            int len = 0;
            int offset = 0;

            System.out.println("Message header is:");
            for (int i = 0; i < 4; i += 1) { // read length header (4 bytes)
                byte b = 0;
                for (int j = 0; j < 8; j += 1) {
                    b |= (data[offset] & 1) << j;
                    offset += 1;
                }
                System.out.print(b);
                System.out.print(' ');
                len |= b << (8 * i);
            }
            System.out.println();

            System.out.println("Decoding message:");
            hiddenBytes = new byte[len];
            for (int i = 0; i < len; i += 1) {
                byte b = 0;
                for (int j = 0; j < 8; j += 1) {
                    b |= (data[offset] & 1) << j;
                    offset += 1;
                }
                hiddenBytes[i] = b;
                System.out.print(b);
                System.out.print(' ');
            }


            System.out.println();
            System.out.println("Encrypted data length is: " + len);
            System.out.println("hiddenbytes"+hiddenBytes);
            String s = new String(hiddenBytes);
            System.out.println("hiddenMessage: "+s);

        } catch (Exception e) {
            Component veiw = null;
            throw new NegativeArraySizeException("ERROR! Please Try Again");
        }
        return hiddenBytes;
    }

    public byte[] intToBytes(int num, int numBytes) {
        byte[] bytes = new byte[numBytes];
        for (int i = 0; i < numBytes; i += 1) {
            bytes[i] = (byte) ((num >> (8 * i)) & 0xFF);
        }
        return bytes;
    }

    private static int bytesToInt(byte[] bytes, int numBytes) {
        int num = 0;
        for (int i = 0; i < numBytes; i += 1) {
            num |= (bytes[i] & 0xFF) << (8 * i);
        }
        return num;
    }

}
