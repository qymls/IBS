import cn.itsource.domain.Department;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class velocity {
    @Test
    public void test() throws Exception {
//创建模板应用上下文
        VelocityContext context = new VelocityContext();
        context.put("name", "测试2");
        context.put("date", new Date());
        context.put("sdf", new SimpleDateFormat("yyyy-MM-dd"));
        Department department = new Department();
        department.setName("asda");
        context.put("department", department);
        /*String[] lists = {"成都", "上海"};
        context.put("lists", lists);*/
        ArrayList<String> lists = new ArrayList<>();
        lists.add("成都");
        lists.add("上海");
        context.put("lists", lists);

        Map<Object, Object> map = new HashMap<>();
        map.put(1, "123");
        map.put(2, 567);
        map.put(3, department);
        context.put("map", map);

        //拿到相应的模板(需要设置好编码)
        Template template = Velocity.getTemplate("hello.vm", "UTF-8");
        //准备输出流
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        System.out.println(writer);

    }
}
