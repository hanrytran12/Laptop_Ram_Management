package Controller;

import Service.InputService;
import Model.RAMItem;
import Service.FileService;
import Service.RAMItemService;
import View.RAMItemView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Class quản lý RAM Items ở trong hệ thống Sử dụng HashMap để lưu RAMItem với String keys(code)
 * Nhận đầu vào của người dùng và triển khai các yêu cầu liên quan tới add, search, update, delete, display và store RAMItem
 *
 * @author Huy
 */
public class RAMManagementSystem extends HashMap<String, RAMItem> {

    //So thu tu de tao RAMItem code
    private int numericalOrder;
    //Services va views su dung trong Controller
    RAMItemService service;
    RAMItemView view;
    FileService fileService;

    /**
     * Constructor khai báo services, view, loads data từ file, và sets số thứ tự
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public RAMManagementSystem() throws FileNotFoundException, IOException, ClassNotFoundException {
        service = new RAMItemService();
        view = new RAMItemView();
        fileService = new FileService();

        //Load từ file RAMModules và set số thứ tự
        fileService.loadFromFile(this);
        numericalOrder = fileService.setNumercialOrder(this);
    }

    /**
     * Add một RAM Item mới vào hệ thống dựa vào dự liệu người dùng nhập
     * Phương thức sẽ yêu cầu người dùng nhập các attribute và tạo code tự động. 
     * Nó sẽ cho phép nhập liên tiếp nếu người dùng muốn.
     */
    public void addItem() {
        boolean isContinue;
        do {
            //Input các attribute cho new RAMItem
            String type = InputService.inputString("Enter type: ");
            String bus = InputService.inputString("Enter bus: ");
            String brand = InputService.inputString("Enter brand: ");
            String quantity = InputService.inputString("Enter quantity: ");
            String production_month_year = InputService.inputString("Enter production_month_year: ");
            //Tạo ra code dựa vào số thứ tự
            String code = "RAM" + type + "_" + Integer.toString(numericalOrder + 1);
            //Nếu như add RAMItem thành công thì cật nhật số thứ tự
            if (service.addItem(this, code, type, bus, brand, quantity, production_month_year)) {
                numericalOrder++;
                System.out.println("SUCCESS!");
            } else {
                System.out.println("FAILURE!");
            }
            //Hỏi người dùng có muốn thêm RAMItem không
            String select = InputService.inputString("Do you want continue to add RAMItem? (Y/N)");
            isContinue = select.equalsIgnoreCase("Y");
        } while (isContinue);
    }

    /**
     * Search RAMItem dựa vào người dùng chọn(type, bus hay brand)
     * Cho phép người dùng chọn và display ra màn hình.
     */
    public void searchItem() {
        boolean isContinue = true;
        do {
            try {
                view.displaySubMenu(); // Display search menu
                int subSelect = InputService.inputInteger("Choose your select: ");
                if (subSelect >= 1 && subSelect <= 3) {
                    service.searchItem(this, subSelect);
                    System.out.println("Enter to bakc to the menu!");
                    isContinue = false;
                } else {
                    System.out.println("WRONG SELECT! PLEASE TRY AGAIN!");
                }
            } catch (NumberFormatException e) {
                System.out.println("INVALID SELECT! PLEASE TRY AGAIN!");
            }

        } while (isContinue);
    }

    /**
     * Updates RAMItem attributes dựa vào dữ liệu người dùng nhập.. Nếu như
     * RAMItem có tồn tại, cật nhật các attribute = value mới
     */
    public void updateItem() {
        String code = InputService.inputString("Enter code: ");
        if (!this.containsKey(code)) {
            System.out.println("RAMItem does not exist!");
        } else {
            RAMItem item = this.get(code);
            //Các giá trị mới của RAMItem
            String type = InputService.inputString("Enter new type: ");
            String bus = InputService.inputString("Enter new bus: ");
            String brand = InputService.inputString("Enter new brand: ");
            String quantity = InputService.inputString("Enter new quantity: ");

            //Update item và thông báo người dùng về kết quả update
            if (service.updateItem(item, type, bus, brand, quantity)) {
                System.out.println("SUCCESS!");
            } else {
                System.out.println("FAILURE!");
            }
            System.out.println("Enter to bakc to the menu!");
        }
    }

    /**
     * Đánh dấu RAMItem thành inactive dựa vào code
     * Người dùng cần confirm trước khi xóa.
     */
    public void deleteItem() {
        String code = InputService.inputString("Enter code: ");
        if (this.containsKey(code)) {
            view.displayConfirmDelete();
            boolean isContinue = true;
            do {
                int select;
                try {
                    select = InputService.inputInteger("Choose your select: ");
                } catch (NumberFormatException e) {
                    System.out.println("INVALID SELECT! PLEASE TRY AGAIN!");
                    continue;
                }
                switch (select) {
                    case 1: {
                        //set RAMItem inactive
                        this.get(code).setActive("false");
                        System.out.println("SUCCESS!");
                        break;
                    }
                    case 0: {
                        System.out.println("SUCCESS!");
                        break;
                    }
                    default: {
                        System.out.println("WRONG SELECT! PLEASE TRY AGAIN!");
                        continue;
                    }
                }
                isContinue = false;
            } while (isContinue);
        } else {
            System.out.println("RAM ITEM IS NOT EXIST!");
        }
    }

    /**
     * Displays các active RAMItems dựa vào lựa chọn của người dùng(sort by Type, Bus or Brand)
     */
    public void displayAllItem() {
        List<RAMItem> listRAMItem = service.getActiveRAMItem(this);
        boolean isContinue = true;
        do {
            try {
                view.displayShowItem(); // Display lựa chọn sort
                int selectSort = InputService.inputInteger("Choose your select: ");
                if (selectSort >= 1 && selectSort <= 3) {
                    listRAMItem = service.showItem(listRAMItem, selectSort);
                    break;
                } else {
                    System.out.println("WRONG SELECT! PLEASE TRY AGAIN!");
                }
            } catch (NumberFormatException e) {
                System.out.println("INVALID SELECT! PLEASE TRY AGAIN!");
            }
        } while (isContinue);
        //Display sorted list của RAMItem
        if (listRAMItem.isEmpty()) {
            System.out.println("");
        } else {
            listRAMItem.forEach(item -> System.out.println(item.getCode()
                    + ", " + item.getType()
                    + ", " + item.getBus()
                    + ", " + item.getBrand()
                    + ", " + item.getQuantity()
                    + ", " + ("active")));
        }
    }

    /**
     * Save các RAMItem vào file dựa vào FileService
     *
     * @throws IOException
     */
    public void storeDataToFile() throws IOException {
        fileService.saveToFile(this);
    }
}
