package Service;

import Model.RAMItem;
import java.util.List;

/**
 * SortService class xử lý việc sắp xếp (sort) các RAMItem trong danh sách theo các tiêu chí khác nhau 
 * như type, bus, và brand.
 * 
 * @author Huy
 */
public class SortService {

    public List<RAMItem> sortByType(List<RAMItem> listRAMItem) {
        listRAMItem.sort((o1, o2) -> {
            return o1.getType().compareTo(o2.getType()); // Sắp xếp theo type của RAMItem.
        });
        return listRAMItem; // Trả về danh sách đã sắp xếp.
    }

    public List<RAMItem> sortByBus(List<RAMItem> listRAMItem) {
        listRAMItem.sort((o1, o2) -> {
            return o1.getBus().compareTo(o2.getBus()); // Sắp xếp theo bus của RAMItem.
        });
        return listRAMItem; // Trả về danh sách đã sắp xếp.
    }

    public List<RAMItem> sortByBrand(List<RAMItem> listRAMItem) {
        listRAMItem.sort((o1, o2) -> {
            return o1.getBrand().compareTo(o2.getBrand()); // Sắp xếp theo brand của RAMItem.
        });
        return listRAMItem; // Trả về danh sách đã sắp xếp.
    }
}
