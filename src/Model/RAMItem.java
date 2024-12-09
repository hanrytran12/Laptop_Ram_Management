package Model;

import java.io.Serializable;

/**
 * Class đại diện cho 1 RAM Item trong system RAM Item có các thuộc tính: code,
 * type, bus, brand, quantity, production_month_year, active Implement
 * Serializable cho phép object lưu vào file RAMModules.dat
 *
 * @author Huy
 */
public class RAMItem implements Serializable {

    private String code;    
    private String type;    
    private String bus;     
    private String brand;   
    private int quantity;  
    private String production_month_year;   
    private String active;                 

    /**
     * Constructor khởi tạo một đối tượng RAMItem với các thông tin cơ bản.
     *
     * @param code Mã RAM Item
     * @param type Loại RAM (VD: LPDDR5, DDR5, ...)
     * @param bus Tốc độ bus của RAM (VD: 5600MHz, 4200MHz, ...)
     * @param brand Thương hiệu RAM (VD: Kingston, SamSung, Corsair, ...)
     * @param quantity Số lượng RAM
     * @param production_month_year Tháng và năm sản xuất (VD: 11/2021)
     * @param active Trạng thái hoạt động của RAM Item (VD: 'true' cho active,
     * 'false' cho không active)
     */
    public RAMItem(String code, String type, String bus, String brand, int quantity, String production_month_year, String active) {
        this.code = code;
        this.type = type;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.production_month_year = production_month_year;
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduction_month_year() {
        return production_month_year;
    }

    public void setProduction_month_year(String production_month_year) {
        this.production_month_year = production_month_year;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    /**
     * Trả về thông tin chi tiết của RAM Item dưới dạng chuỗi. Chuỗi định dạng
     * gồm các thông tin: code, type, bus, brand, quantity,
     * production_month_year, active
     *
     * @return Chuỗi thông tin của RAM Item
     */
    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %d, %s, %s", code, type, bus, brand, quantity, production_month_year, active);
    }
}
