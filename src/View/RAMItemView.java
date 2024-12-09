package View;

/**
 * Class dùng để display các giao diện menu
 *
 * @author Huy
 */
public class RAMItemView {
    
    /**
     * Hiển thị menu để lựa chọn các select xử lý RAM Item
     */
    public void displayMainMenu() {
        System.out.println("============================================"
                + "\n=           RAM MANAGEMENT SYSTEM          ="
                + "\n============================================"
                + "\n=   1. ADD ITEM                            ="
                + "\n=   2. SEARCH ITEM                         ="
                + "\n=   3. UPDATE ITEM                         ="
                + "\n=   4. DELETE ITEM                         ="
                + "\n=   5. SHOW ALL ITEMS                      ="
                + "\n=   6. STORE DATA TO FILES                 ="
                + "\n=   7. QUIT MENU                           ="
                + "\n============================================");
    }

    /**
     * Hiển thị sub menu để lựa chọn kiểu tìm kiếm RAMItem 
     * Người dùng có thể chọn tìm theo type, bus hoặc brand.
     */
    public void displaySubMenu() {
        System.out.println("============================================"
                + "\n=              SEARCH MENU                 ="
                + "\n============================================"
                + "\n=   1. SEARCH BY TYPE                      ="
                + "\n=   2. SEARCH BY BUS                       ="
                + "\n=   3. SEARCH BY BRAND                     ="
                + "\n============================================");
    }

    /**
     * Hiển thị menu để lựa chọn có muốn xóa RAMItem
     * Người dùng chọn 0 nếu không muốn xóa, 1 nếu muốn xóa.
     */
    public void displayConfirmDelete() {
        System.out.println("================================================"
                + "\n=  ARE YOU SURE WANT TO DELETE THIS RAM ITEM?  ="
                + "\n=        0: NO         |        1: YES         ="
                + "\n================================================");
    }

    /**
     * Hiển thị menu để lựa chọn show thông tin RAMItem bằng cách sort theo attribute(type, bus, brand) 
     * Người dùng chọn sort theo type, bus hoặc brand.
     */
    public void displayShowItem() {
        System.out.println("============================================"
                + "\n=                SHOW MENU                 ="
                + "\n============================================"
                + "\n=   1. SORT BY TYPE                        ="
                + "\n=   2. SORT BY BUS                         ="
                + "\n=   3. SORT BY BRAND                       ="
                + "\n============================================");
    }
}
