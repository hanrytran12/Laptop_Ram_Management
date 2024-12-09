package Service;

import Controller.RAMManagementSystem;
import Model.RAMItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * RAMItemService class xử lý các logic nghiệp vụ chính về add, search, update, show
 * của các RAM items trong hệ thống quản lý.
 * 
 * @author Huy
 */
public class RAMItemService {

    // Tạo các instance của ValidationService, SearchService, và SortService
    // để sử dụng cho việc kiểm tra hợp lệ, tìm kiếm, và sắp xếp các RAM item.
    private final ValidationService validationService = new ValidationService();
    private final SearchService searchService = new SearchService();
    private final SortService sortService = new SortService();

    /**
     * Thêm một RAM item mới vào danh sách nếu các dữ liệu nhập vào hợp lệ.
     * 
     * @param listRAMItem Danh sách chứa các RAM item.
     * @param code Mã RAM item (được tự động tạo).
     * @param type Kiểu RAM (VD: DDR4, LPDDR5, ...).
     * @param bus Tốc độ bus của RAM (VD: 3200MHz, 5600MHz, ...).
     * @param brand Thương hiệu RAM (VD: Kingston, Samsung, ...).
     * @param quantity Số lượng RAM.
     * @param productionMonthYear Tháng/năm sản xuất của RAM.
     * @return true nếu RAM item được thêm thành công, false nếu dữ liệu không hợp lệ.
     */
    public boolean addItem(RAMManagementSystem listRAMItem, String code, String type, String bus, String brand, String quantity, String productionMonthYear) {
        // Kiểm tra tính hợp lệ của các dữ liệu nhập vào.
        if (validationService.isValidData(type, bus, brand, quantity, productionMonthYear)) {
            // Thêm RAM item vào danh sách nếu dữ liệu hợp lệ.
            listRAMItem.put(code, new RAMItem(code, type, bus, brand, Integer.parseInt(quantity), productionMonthYear, "true"));
            return true;
        }
        return false;
    }

    /**
     * Tìm kiếm các RAM item trong danh sách dựa vào các tiêu chí khác nhau
     * (type, bus, hoặc brand) tùy theo lựa chọn của người dùng.
     * 
     * @param listRAMItem Danh sách chứa các RAM item.
     * @param subSelect Lựa chọn tìm kiếm (1 - tìm theo type, 2 - tìm theo bus, 3 - tìm theo brand).
     */
    public void searchItem(RAMManagementSystem listRAMItem, int subSelect) {
        switch (subSelect) {
            case 1: {
                // Tìm kiếm theo kiểu RAM (type).
                String type = InputService.inputString("Enter type: ");
                searchService.searchByType(type, listRAMItem);
                break;
            }
            case 2: {
                // Tìm kiếm theo tốc độ bus.
                String bus = InputService.inputString("Enter bus: ");
                searchService.searchByBus(bus, listRAMItem);
                break;
            }
            case 3: {
                // Tìm kiếm theo thương hiệu.
                String brand = InputService.inputString("Enter brand: ");
                searchService.searchByBrand(brand, listRAMItem);
                break;
            }
        }
    }

    /**
     * Cập nhật thông tin của một RAM item nếu các dữ liệu nhập vào hợp lệ.
     * Nếu dữ liệu nhập vào trống, giữ lại dữ liệu hiện tại của item đó.
     * 
     * @param item RAM item cần được cập nhật.
     * @param type Kiểu RAM mới (nếu có).
     * @param bus Tốc độ bus mới (nếu có).
     * @param brand Thương hiệu mới (nếu có).
     * @param quantity Số lượng mới (nếu có).
     * @return true nếu cập nhật thành công, false nếu dữ liệu mới không hợp lệ.
     */
    public boolean updateItem(RAMItem item, String type, String bus, String brand, String quantity) {
        // Nếu dữ liệu nhập vào trống, giữ lại giá trị hiện tại của RAM item.
        type = (type.isEmpty()) ? item.getType() : type;
        bus = (bus.isEmpty()) ? item.getBus() : bus;
        brand = (brand.isEmpty()) ? item.getBrand() : brand;
        quantity = (quantity.isEmpty()) ? Integer.toString(item.getQuantity()) : quantity;

        // Kiểm tra tính hợp lệ của các dữ liệu mới.
        if (validationService.isValidDataToUpdate(type, bus, brand, quantity)) {
            // Cập nhật thông tin cho RAM item nếu hợp lệ.
            item.setType(type);
            item.setBus(bus);
            item.setBrand(brand);
            item.setQuantity(Integer.parseInt(quantity));
            return true;
        }
        return false;
    }

    /**
     * Lấy tất cả các RAM item có trạng thái "active" từ danh sách.
     * 
     * @param listRAMItem Danh sách chứa các RAM item.
     * @return Danh sách các RAM item đang active.
     */
    public List<RAMItem> getActiveRAMItem(RAMManagementSystem listRAMItem) {
        List<RAMItem> listActiveRAMItem = new ArrayList<>();
        // Duyệt qua tất cả các RAM item trong danh sách và chỉ thêm những item có trạng thái "active".
        for (Map.Entry<String, RAMItem> entry : listRAMItem.entrySet()) {
            if (entry.getValue().getActive().equalsIgnoreCase("true")) {
                listActiveRAMItem.add(entry.getValue());
            }
        }
        return listActiveRAMItem;
    }

    /**
     * Hiển thị danh sách các RAM item dựa trên tiêu chí sắp xếp được chọn.
     * 
     * @param listRAMItem Danh sách chứa các RAM item đang active.
     * @param selectSort Lựa chọn sắp xếp (1 - theo type, 2 - theo bus, 3 - theo brand).
     * @return Danh sách RAM item đã được sắp xếp.
     */
    public List<RAMItem> showItem(List<RAMItem> listRAMItem, int selectSort) {
        switch (selectSort) {
            case 1: {
                // Sắp xếp theo kiểu RAM (type).
                listRAMItem = sortService.sortByType(listRAMItem);
                break;
            }
            case 2: {
                // Sắp xếp theo tốc độ bus.
                listRAMItem = sortService.sortByBus(listRAMItem);
                break;
            }
            case 3: {
                // Sắp xếp theo thương hiệu.
                listRAMItem = sortService.sortByBrand(listRAMItem);
                break;
            }
        }
        return listRAMItem;
    }
}
