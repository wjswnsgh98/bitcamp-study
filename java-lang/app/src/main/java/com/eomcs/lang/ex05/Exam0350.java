package com.eomcs.lang.ex05;

//# 비트 연산자(&, |, ^, ~)
//
public class Exam0350 {
  public static void main(String[] args) {
    int a = 0b0110_1100;
    int b = 0b0101_0101;

    // 정수 값에 대해서는 &&와 ||, !을 사용할 수 없다.
    // System.out.println(a && b); // 컴파일 오류!
    // System.out.println(a || b); // 컴파일 오류!
    // System.out.println(!a); // 컴파일 오류!

    
    // 그러나 &, |, ^, ~는 사용할 수 있다.
    // => 각 비트 단위로 연산을 수행한다.
    // => 1은 true, 0은 false라고 간주하고 계산한다.
    // => 출력 결과도 정수이다.

    System.out.println(a & b);
    // a = 0000 0000 0000 0000 0000 0000 0110 1100
    // b = 0000 0000 0000 0000 0000 0000 0101 0101
    // --------------------------------------------
    //     0000 0000 0000 0000 0000 0000 0100 0100 = 68

    System.out.println(a | b);
    // a = 0000 0000 0000 0000 0000 0000 0110 1100
    // b = 0000 0000 0000 0000 0000 0000 0101 0101
    // --------------------------------------------
    //     0000 0000 0000 0000 0000 0000 0111 1101 = 125

    System.out.println(a ^ b);
    // a = 0000 0000 0000 0000 0000 0000 0110 1100
    // b = 0000 0000 0000 0000 0000 0000 0101 0101
    // --------------------------------------------
    //     0000 0000 0000 0000 0000 0000 0011 1001 = 57

    // 비트 연산에서 not은 ! 연산자가 아니라 ~ 연산자 이다.
    System.out.println(~a);
    // a = 0000 0000 0000 0000 0000 0000 0110 1100
    // --------------------------------------------
    //     1111 1111 1111 1111 1111 1111 1001 0011 = -109
  }
}

// 정리!
// => 도대체 비트 연산자는 어디에 쓰이는가?
//    - 이미지 및 영상 처리에 사용된다.
//    - 예) 마스킹, 오버레이 기법에 사용된다.
//    - 예) 색조 변경에 사용된다.
//
// 사진을 컴퓨터에 저장하려면 2진수화 시켜야 한다.
// 일단 사진을 점(픽셀; pixel)으로 바꾼다.
// 각 픽셀의 색상은 RGB의 값(빛의 삼원색)으로 표현한다.
// 즉 빨강,파랑,초록의 색 세기를 조정하여 다양한 색상을 표현하는 것이다.
// 보통 천연색을 표현하기 위해 RGB 각 색상을 8비트로 표현한다.
// 빨강의 빛의 세기: 0 ~ 255 => 0000 0000 ~ 1111 1111
// 파랑의 빛의 세기: 0 ~ 255 => 0000 0000 ~ 1111 1111
// 초록의 빛의 세기: 0 ~ 255 => 0000 0000 ~ 1111 1111
// 이 3개의 값을 합쳐서 한 개의 픽셀 색상을 표현하는 것이다.
// 즉 24비트(3바이트)로 픽셀 색상을 표현하는 것이다.
// 빨강 색의 한 픽셀: 1111 1111 0000 0000 0000 0000 = 0xFF0000
// 초록 색의 한 픽셀: 0000 0000 1111 1111 0000 0000 = 0x00FF00
// 파랑 색의 한 픽셀: 0000 0000 0000 0000 1111 1111 = 0x0000FF
// 노랑 색의 한 픽셀: 1111 1111 1111 1111 0000 0000 = 0xFFFF00
//
// 비트 연산 활용 예1:
// => 화면을 좀 더 엔틱하게 표현하고 싶다.
//    누리끼리하게 만들고 싶다.
// => 방법: 노란색 계통을 강화하는 것이다.
//    즉 빨랑, 초록 빛을 강화하면 된다.
// => 다음과 같이 한 개의 점이 있다. 엔틱하게 만들자
//    10001100 10010010 10101100 (변경전 색)
//  | 10010010 01001001 00000000
// -------------------------------
//    10011110 11011011 10101100 (변견한 색)
//
// 비트 연산 활용 예2:
// => 화면을 좀 더 시원하게, 푸르스름하게 만들자.
// => 방법: 파란색 계통을 강화(|)하거나
//          빨랑이나 초록을 약화(&)시키면 된다.
// => 다음과 같이 한 개의 점이 있다.
//    10001100 10010010 10101100 (변경전 색)
//  & 11011011 11011011 11111111 (빨강색과 초록색을 약화시키기)
// -------------------------------
//    10001000 10010000 10101100 (변견한 색)
//
// 비트 연산 활용 예3:
// => 흑백을 반전시키자
// => 방법:
//       빛이 있는 점은 빛을 없애고 (1 --> 0)
//       빛이 없는 점은 빛을 넣는다.(0 --> 1)
// => 다음과 같이 흑색의 한 점이 있다.
//   00000000 00000000 00000000 (변경전 색: 검정)
// ~
// -------------------------------
//   11111111 11111111 11111111 (변견한 색: 흰색)
//
// 비트 연산 활용 예4:
// => 특정 색상만 제거하기
// => 방법:
//    ^ 연산자를 사용하여 특정 색상만 제거할 수 있다.
// => 다음과 같이 비트 데이터가 있다.
//   11001010 11100100 00111001 (예제 색상 )
// ^ 11001010 11100100 00111001 (제거하려는 색상)
// -------------------------------
//   00000000 00000000 00000000 (출력 결과)
// => 한 점에 대해 연산한 출력 결과가 0이면 해당 점의 색상을 제거하고,
//    만약 0이 아니면 원래 색상을 그대로 사용한다.
//

// 1920 * 1080 = 207_3600 픽셀
//
// 1 픽셀
// Red(8bit) + Green(8bit) + Blue(8bit)
// = 3 byte/pixel
// = 1677_7216 색상
//
// 207_3600 pixel * 3byte = 622_8900 byte = 6082 KB = 5.94MB
//
// 1000_0000 * 3 = 3000_0000 = 29_296 KB = 28.6MB(raw data file)
//
// 80분 영화
// = 80분 * 60(초) * 24(장)/초
// = 11_5200(장)
// 용량
// == 115200 * 6MB = 691200MB = 675GB ==> 4GB(손실압축, MPEG)