package Service;

import java.util.Scanner;

/**
 * InputService class xử lý việc nhập dữ liệu từ người dùng thông qua giao diện console.
 * Class cung cấp các phương thức để nhập số nguyên và chuỗi từ người dùng.
 * 
 * @author Huy
 */
public class InputService {

    /**
     * Phương thức inputInteger yêu cầu người dùng nhập vào một số nguyên.
     * Hiển thị một dòng thông báo và nhận giá trị số nguyên từ người dùng.
     * 
     * @param text Chuỗi thông báo được hiển thị để yêu cầu người dùng nhập dữ liệu.
     * @return Trả về giá trị số nguyên mà người dùng đã nhập.
     * @throws NumberFormatException nếu người dùng nhập không phải là số nguyên hợp lệ.
     */
    public static int inputInteger(String text) {
        System.out.println(text); 
        Scanner ip = new Scanner(System.in); 
        int number = Integer.parseInt(ip.next()); 
        return number; 
    }

    /**
     * Phương thức inputString yêu cầu người dùng nhập vào một chuỗi.
     * Hiển thị một dòng thông báo và nhận giá trị chuỗi từ người dùng.
     * 
     * @param text Chuỗi thông báo được hiển thị để yêu cầu người dùng nhập dữ liệu.
     * @return Trả về chuỗi ký tự mà người dùng đã nhập.
     */
    public static String inputString(String text) {
        System.out.println(text); 
        Scanner ip = new Scanner(System.in); 
        return ip.nextLine(); 
    }
}
