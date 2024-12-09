package Service;

/**
 * Class ValidationService dùng để kiểm tra tính hợp lệ của các attribute trong RAMItem.
 * Các phương thức trong class này kiểm tra dữ liệu đầu vào trước khi thêm mới hoặc cập nhật.
 * 
 * @author Huy
 */
public class ValidationService {

    /**
     * Phương thức kiểm tra tính hợp lệ của tất cả các attribute của RAMItem.
     * Bao gồm: type, bus, brand, quantity, và production_month_year.
     * 
     * @param type Kiểu RAM (VD: LPDDR, DDR).
     * @param bus Tốc độ bus của RAM (VD: 2400MHz).
     * @param brand Thương hiệu RAM.
     * @param quantity Số lượng RAM.
     * @param production_month_year Tháng và năm sản xuất (VD: 04/2021).
     * @return Trả về true nếu tất cả các attribute hợp lệ, ngược lại trả về false.
     */
    public boolean isValidData(String type, String bus, String brand, String quantity, String production_month_year) {
        return isValidType(type) && isValidBus(bus) && !isValidBrand(brand) && isValidQuantity(quantity) && isValidProductionMonthYear(production_month_year);
    }

    /**
     * Phương thức kiểm tra tính hợp lệ khi cập nhật RAMItem.
     * Không kiểm tra ngày sản xuất do ngày này không thay đổi khi cập nhật.
     * 
     * @param type Kiểu RAM.
     * @param bus Tốc độ bus.
     * @param brand Thương hiệu RAM.
     * @param quantity Số lượng RAM.
     * @return Trả về true nếu các attribute hợp lệ, ngược lại trả về false.
     */
    public boolean isValidDataToUpdate(String type, String bus, String brand, String quantity) {
        return isValidType(type) && isValidBus(bus) && isValidQuantity(quantity);
    }

    public boolean isValidType(String type) {
        return (type.matches("(LPDDR|DDR)\\d"));
    }

    public boolean isValidBus(String bus) {
        return (bus.matches("[1-9]\\d{3}MHz"));
    }

    public boolean isValidBrand(String brand) {
        return (brand.isEmpty());
    }

    public boolean isValidQuantity(String quantity) {
        try {
            int isNumber = Integer.parseInt(quantity);
            if (isNumber <= 0) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isValidProductionMonthYear(String productionMonthYear) {
        return productionMonthYear.matches("^(0[1-9]|1[0-2])\\/(200[0-9]|201[0-9]|202[0-4])$");
    }
}
