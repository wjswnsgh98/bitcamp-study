package m_project.handler;

import m_project.vo.Member;
import util.ActionListener;
import util.BreadcrumbPrompt;
import java.util.List;

public abstract class AbstractMemberListener implements ActionListener{
    protected List<Member> list;

    public AbstractMemberListener(List<Member> list){
        this.list = list;
    }

    protected static String toGenderString(char gender){
        return gender == 'M' ? "남성" : "여성";
    }

    protected Member findBy(int no){
        for(int i = 0; i < this.list.size(); i++){
          Member m = this.list.get(i);
          if(m.getBook_no() == no){
            return m;
          }
        }
        return null;
      }

    protected char inputGender(char gender, BreadcrumbPrompt prompt){
        String label;
        if(gender == 0){
            label = "성별?\n";
        } else{
            label = String.format("성별(%s)?\n", toGenderString(gender));
        }

        while(true){
            String menuNo = prompt.inputString(label +
                " 1. 남자\n" +
                " 2. 여자\n" +
                "> ");

            switch(menuNo){
            case "1":
                return Member.MALE;
            case "2":
                return Member.FEMALE;
            default:
                System.out.println("무효한 번호입니다.");
            }
        }
    }
}
