package Service;

import Controller.RAMManagementSystem;
import Model.RAMItem;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * FileService class xử lý các logic liên quan đến việc lưu trữ và tải RAM items từ file.
 * Cụ thể là lưu trữ và tải dữ liệu dưới dạng nhị phân (binary file).
 * 
 * @author Huy
 */
public class FileService {

    public int setNumercialOrder(RAMManagementSystem listRAMItem) {
        // Nếu danh sách trống, trả về 0.
        if (listRAMItem.isEmpty()) {
            return 0;
        }
        int numercialOrderMax = Integer.MIN_VALUE;
        // Duyệt qua danh sách các RAM item để tìm số thứ tự lớn nhất.
        for (Map.Entry<String, RAMItem> entry : listRAMItem.entrySet()) {
            int numercialOrder = getNumercialOrder(entry.getKey());
            if (numercialOrder > numercialOrderMax) {
                numercialOrderMax = numercialOrder;
            }
        }
        return numercialOrderMax;
    }

    /**
     * Phương thức getNumercialOrder được sử dụng để lấy số thứ tự từ key của một RAM item.
     * Key có định dạng "RAM + Type_ + <numercialOrder>".
     * 
     * @param key Chuỗi key của RAM item.
     * @return Số thứ tự (numerical order) được tách ra từ key.
     */
    private int getNumercialOrder(String key) {
        int pos = 0;
        // Xác định vị trí dấu gạch dưới (_) trong key.
        while (key.charAt(pos) != '_') {
            pos++;
        }
        // Trích xuất và chuyển đổi phần số thứ tự từ key.
        return Integer.parseInt(key.substring(pos + 1));
    }

    /**
     * Phương thức saveToFile được sử dụng để lưu trữ danh sách RAM item vào file "RAMModules.dat".
     * Dữ liệu được lưu dưới dạng nhị phân (binary).
     * 
     * @param listRAMItem Danh sách chứa các RAM item cần lưu trữ.
     * @throws FileNotFoundException Nếu file không được tìm thấy.
     * @throws IOException Nếu có lỗi trong quá trình ghi file.
     */
    public void saveToFile(RAMManagementSystem listRAMItem) throws FileNotFoundException, IOException {
        // Sử dụng try-with-resources để tự động đóng FileOutputStream và ObjectOutputStream.
        try (FileOutputStream fileOut = new FileOutputStream("RAMModules.dat");
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            // Duyệt qua danh sách các RAM item và ghi từng item vào file.
            for (Map.Entry<String, RAMItem> entry : listRAMItem.entrySet()) {
                objectOut.writeObject(entry.getValue());
            }
            System.out.println("RAM items map has been saved to RAMModules.dat");
        } catch (IOException e) {
            // Xử lý lỗi trong quá trình ghi file.
            System.err.println("Error while saving RAM items map to file: " + e.getMessage());
        }
    }

    /**
     * Phương thức loadFromFile được sử dụng để tải danh sách RAM item từ file "RAMModules.dat".
     * Các RAM item sẽ được đọc và thêm vào danh sách (RAMManagementSystem).
     * 
     * @param listRAMItem Danh sách sẽ chứa các RAM item sau khi được tải.
     * @throws FileNotFoundException Nếu file không được tìm thấy.
     * @throws IOException Nếu có lỗi trong quá trình đọc file.
     * @throws ClassNotFoundException Nếu không tìm thấy class RAMItem trong khi đọc đối tượng.
     */
    public void loadFromFile(RAMManagementSystem listRAMItem) throws FileNotFoundException, IOException, ClassNotFoundException {
        // Sử dụng try-with-resources để tự động đóng FileInputStream và ObjectInputStream.
        try (FileInputStream fileIn = new FileInputStream("RAMModules.dat");
                ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            try {
                // Đọc từng RAM item từ file và thêm vào danh sách cho đến khi gặp EOFException (kết thúc file).
                while (true) {
                    RAMItem ramItem = (RAMItem) objectIn.readObject();
                    listRAMItem.put(ramItem.getCode(), ramItem);
                }
            } catch (EOFException e) {
                // Kết thúc việc đọc file khi EOFException xảy ra (đã đọc hết file).
            }

            System.out.println("RAM items map has been loaded from RAMModules.dat");

        } catch (IOException | ClassNotFoundException e) {
            // Xử lý lỗi trong quá trình đọc file hoặc tải đối tượng.
            System.err.println("Error while loading RAM items from file: " + e.getMessage());
        }
    }
}
