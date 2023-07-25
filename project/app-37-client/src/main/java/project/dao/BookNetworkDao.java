package project.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import net.RequestEntity;
import net.ResponseEntity;
import project.vo.Book;

public class BookNetworkDao implements BookDao{
  String dataName;
  DataInputStream in;
  DataOutputStream out;

  public BookNetworkDao(String dataName, DataInputStream in, DataOutputStream out) {
    this.dataName = dataName;
    this.in = in;
    this.out = out;
  }

  @Override
  public void insert(Book book) {
    try {
      // 서버에 요청을 보낸다.
      out.writeUTF(new RequestEntity()
          .command(dataName + "/insert")
          .data(book)
          .toJson());

      // 서버에서 보낸 응답을 받는다.
      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());
      if (response.getStatus().equals(ResponseEntity.ERROR)) {
        throw new RuntimeException(response.getResult());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Book> list() {
    try {
      out.writeUTF(new RequestEntity()
          .command(dataName + "/list")
          .toJson());

      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());
      if (response.getStatus().equals(ResponseEntity.FAILURE)) {
        throw new RuntimeException(response.getResult());
      }

      return response.getList(Book.class);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Book findBy(String str) {
    try {
      out.writeUTF(new RequestEntity()
          .command(dataName + "/findBy")
          .data(str)
          .toJson());

      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());

      if (response.getStatus().equals(ResponseEntity.ERROR)) {
        throw new RuntimeException(response.getResult());
      } else if (response.getStatus().equals(ResponseEntity.FAILURE)) {
        return null;
      }

      return response.getObject(Book.class);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String delete(String str) {
    try {
      out.writeUTF(new RequestEntity()
          .command(dataName + "/delete")
          .data(str)
          .toJson());

      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());

      if (response.getStatus().equals(ResponseEntity.ERROR)) {
        throw new RuntimeException(response.getResult());
      } else if (response.getStatus().equals(ResponseEntity.FAILURE)) {
        return null;
      }

      return response.getObject(String.class);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}