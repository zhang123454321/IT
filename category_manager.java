import java.util.*;

/**
 * 分类管理器，用于新增、编辑、删除、查看分类。
 */
public class CategoryManager {

    private static final int MAX_CATEGORY_COUNT = 100;

    private static final List<Category> category_list = new ArrayList<>();

    private static int category_id_counter = 1;

    /**
     * 分类类定义
     */
    static class Category {
        int id;
        String name;
        String description;

        public Category(int id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        public void set_name(String name) {
            this.name = name;
        }

        public void set_description(String description) {
            this.description = description;
        }

        public int get_id() {
            return this.id;
        }

        public String get_name() {
            return this.name;
        }

        public String get_description() {
            return this.description;
        }

        @Override
        public String toString() {
            return "分类ID: " + id + " | 名称: " + name + " | 描述: " + description;
        }
    }

    public static String create_category(String name, String description) {
        if (category_list.size() >= MAX_CATEGORY_COUNT) {
            return "Error: 分类数量已达上限（" + MAX_CATEGORY_COUNT + "）";
        }
        if (!is_name_unique(name)) {
            return "Error: 分类名称已存在。";
        }
        Category new_category = new Category(category_id_counter++, name, description);
        category_list.add(new_category);
        return "分类 [" + name + "] 创建成功。";
    }

    public static String update_category(int id, String new_name, String new_description) {
        for (Category category : category_list) {
            if (category.get_id() == id) {
                if (!category.get_name().equals(new_name) && !is_name_unique(new_name)) {
                    return "Error: 新的分类名称已存在。";
                }
                category.set_name(new_name);
                category.set_description(new_description);
                return "分类 [" + id + "] 更新成功。";
            }
        }
        return "Error: 未找到ID为 " + id + " 的分类。";
    }

    public static String delete_category(int id) {
        Iterator<Category> iterator = category_list.iterator();
        while (iterator.hasNext()) {
            Category category = iterator.next();
            if (category.get_id() == id) {
                iterator.remove();
                return "分类 [" + id + "] 删除成功。";
            }
        }
        return "Error: 未找到ID为 " + id + " 的分类。";
    }

    public static String find_category_by_name(String name) {
        for (Category category : category_list) {
            if (category.get_name().equalsIgnoreCase(name)) {
                return "找到分类：" + category.toString();
            }
        }
        return "未找到名为 [" + name + "] 的分类。";
    }

    public static void print_all_categories() {
        if (category_list.isEmpty()) {
            System.out.println("当前无任何分类。");
            return;
        }
        System.out.println("=== 所有分类列表 ===");
        for (Category category : category_list) {
            System.out.println(category.toString());
        }
    }

    private static boolean is_name_unique(String name) {
        for (Category category : category_list) {
            if (category.get_name().equalsIgnoreCase(name)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(create_category("技术", "技术相关文章"));
        System.out.println(create_category("生活", "生活分享"));
        System.out.println(create_category("技术", "重复名称"));

        print_all_categories();

        System.out.println(update_category(1, "科技", "更新后的技术分类"));
        System.out.println(update_category(99, "不存在", "测试无效ID"));

        System.out.println(find_category_by_name("科技"));
        System.out.println(find_category_by_name("健康"));

        System.out.println(delete_category(2));
        System.out.println(delete_category(99));

        print_all_categories();

        // 模拟创建多个分类达到上限
        for (int i = 0; i < 98; i++) {
            System.out.println(create_category("分类" + i, "描述" + i));
        }

        System.out.println(create_category("最后一个", "测试上限"));
        System.out.println(create_category("超出限制", "不应被创建"));
    }
}
