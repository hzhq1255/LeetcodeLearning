package solution;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hzhq
 * @since 2022/6/4 16:10
 * 929 独特的邮件地址
 * 
 * 每个 有效电子邮件地址 都由一个 本地名 和一个 域名 组成，以 '@' 符号分隔。除小写字母之外，电子邮件地址还可以含有一个或多个 '.' 或 '+' 。
 * 例如，在 alice@leetcode.com中， alice 是 本地名 ，而 leetcode.com 是 域名 。
 * 如果在电子邮件地址的 本地名 部分中的某些字符之间添加句点（'.'），则发往那里的邮件将会转发到本地名中没有点的同一地址。请注意，此规则 不适用于域名 。

 * 例如，"alice.z@leetcode.com” 和 “alicez@leetcode.com” 会转发到同一电子邮件地址。
 * 如果在 本地名 中添加加号（'+'），则会忽略第一个加号后面的所有内容。这允许过滤某些电子邮件。同样，此规则 不适用于域名 。

 * 例如 m.y+name@email.com 将转发到 my@email.com。
 * 可以同时使用这两个规则。

 * 给你一个字符串数组 emails，我们会向每个 emails[i] 发送一封电子邮件。返回实际收到邮件的不同地址数目。

 *  

 * 示例 1：

 * 输入：emails = ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
 * 输出：2
 * 解释：实际收到邮件的是 "testemail@leetcode.com" 和 "testemail@lee.tcode.com"。
 * 示例 2：

 * 输入：emails = ["a@leetcode.com","b@leetcode.com","c@leetcode.com"]
 * 输出：3
 */
public class Solution929 {
    
     

    public static int numUniqueEmailsMethod1(String[] emails) {

        Set<String> recviers = new HashSet<>();
        for(String email: emails){
            
            if(email.matches("[a-z\\+\\.]+@[a-z\\+]+(\\.[a-z\\+]+)+")){
                String emailPrefix = email.split("@")[0];
                emailPrefix = emailPrefix.replace(".", "");
                if ( emailPrefix.contains("+")){
                    emailPrefix = emailPrefix.split("\\+")[0];
                }
                recviers.add(emailPrefix + "@" + email.split("@")[1]);
            }
        }
        return recviers.size();
    }

    public static int numUniqueEmailsMethod2(String[] emails){
        Set<String> recviers = new HashSet<>();

        for(String email: emails){
            int index = email.indexOf("@");
            String emailPrefix = email.substring(0, index);
            emailPrefix = emailPrefix.replace(".","");
            int plusIndex = emailPrefix.indexOf("+");
            if (plusIndex != -1){
                emailPrefix = emailPrefix.substring(0, plusIndex);
            }
            System.out.println(emailPrefix + email.substring(index));
            recviers.add(emailPrefix + email.substring(index));
        }

        return recviers.size();
    }


    public static int numUniqueEmailsMethod3(String[] emails){
        Set<String> set = new HashSet<>();
        for (String s : emails) {
            StringBuilder sb = new StringBuilder();
            int n = s.length(), i = 0;
            boolean ok = true;
            while (i < n) {
                char c = s.charAt(i);
                if (c == '@') break;
                if (c == '.' && ++i >= 0) continue;
                if (c == '+') ok = false;
                if (ok) sb.append(c);
                i++;
            }
            set.add(sb.append(s.substring(i)).toString());
        }
        return set.size();
    }


    
    public static void main(String[] args) {
        String[] emails = new String[]{
            "test.email+alex@leetcode.com",
            "test.e.mail+bob.cathy@leetcode.com",
            "testemail+david@lee.tcode.com"
        };
        System.out.println(numUniqueEmailsMethod1(emails));

        System.out.println(numUniqueEmailsMethod2(emails));
    }
}
