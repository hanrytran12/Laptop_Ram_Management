package Service;

import Controller.RAMManagementSystem;
import Model.RAMItem;
import java.util.Map;

/**
 * SearchService class xử lý các logic tìm kiếm (search) trên danh sách các RAM item (listRAMItem).
 * Các phương thức trong class này tìm kiếm RAM item dựa trên type, bus, và brand.
 * 
 * @author Huy
 */
public class SearchService {

    public void searchByType(String type, RAMManagementSystem listRAMItem) {
        boolean isExist = false; // Biến flag để kiểm tra xem có tìm thấy RAM item nào không
        // Duyệt qua từng entry trong danh sách RAM item
        for (Map.Entry<String, RAMItem> entry : listRAMItem.entrySet()) {
            // Kiểm tra nếu type của RAM item khớp với type được cung cấp (không phân biệt hoa thường)
            if (entry.getValue().getType().equalsIgnoreCase(type)) {
                isExist = true; // Đánh dấu đã tìm thấy RAM item
                // In ra thông tin của RAM item tìm thấy
                System.out.println(entry.getValue().getCode() + ", " + entry.getValue().getType() + ", " 
                        + entry.getValue().getProduction_month_year() + ", " + entry.getValue().getQuantity());
            }
        }
        // Nếu không tìm thấy RAM item nào, in ra chuỗi rỗng
        if (!isExist) {
            System.out.println("");
        }
    }

    public void searchByBus(String bus, RAMManagementSystem listRAMItem) {
        boolean isExist = false; // Biến flag để kiểm tra xem có tìm thấy RAM item nào không
        // Duyệt qua từng entry trong danh sách RAM item
        for (Map.Entry<String, RAMItem> entry : listRAMItem.entrySet()) {
            // Kiểm tra nếu bus của RAM item khớp với bus được cung cấp (không phân biệt hoa thường)
            if (entry.getValue().getBus().equalsIgnoreCase(bus)) {
                isExist = true; // Đánh dấu đã tìm thấy RAM item
                // In ra thông tin của RAM item tìm thấy
                System.out.println(entry.getValue().getCode() + ", " + entry.getValue().getType() + ", " 
                        + entry.getValue().getProduction_month_year() + ", " + entry.getValue().getQuantity());
            }
        }
        // Nếu không tìm thấy RAM item nào, in ra chuỗi rỗng
        if (!isExist) {
            System.out.println("");
        }
    }

    public void searchByBrand(String brand, RAMManagementSystem listRAMItem) {
        boolean isExist = false; // Biến flag để kiểm tra xem có tìm thấy RAM item nào không
        // Duyệt qua từng entry trong danh sách RAM item
        for (Map.Entry<String, RAMItem> entry : listRAMItem.entrySet()) {
            // Kiểm tra nếu brand của RAM item khớp với brand được cung cấp (không phân biệt hoa thường)
            if (entry.getValue().getBrand().equalsIgnoreCase(brand)) {
                isExist = true; // Đánh dấu đã tìm thấy RAM item
                // In ra thông tin của RAM item tìm thấy
                System.out.println(entry.getValue().getCode() + ", " + entry.getValue().getType() + ", " 
                        + entry.getValue().getProduction_month_year() + ", " + entry.getValue().getQuantity());
            }
        }
        // Nếu không tìm thấy RAM item nào, in ra chuỗi rỗng
        if (!isExist) {
            System.out.println("");
        }
    }
}
