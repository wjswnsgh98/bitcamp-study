package project.dao;

import java.util.List;
import project.vo.Member;

public interface MemberDao {
  void insert(Member member);
  List<Member> list();
  Member findBy(int no);
  Member findByEmailAndPassword(Member m);
  int update(Member member);
  int delete(int no);
}