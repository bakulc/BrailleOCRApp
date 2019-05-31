
int rows[3] = {36, 34, 32};
int cols[6] = {8, 9, 10, 11, 12, 13};

int braille[26][3][2] = {
  {{1,0},{0,0},{0,0}}, {{1,0},{1,0},{0,0}}, {{1,1},{0,0},{0,0}}, // abc
  {{1,1},{0,1},{0,0}}, {{1,0},{0,1},{0,0}}, {{1,1},{1,0},{0,0}}, // def
  {{1,1},{1,1},{0,0}}, {{1,0},{1,1},{0,0}}, {{0,1},{1,0},{0,0}}, // ghi
  {{0,1},{1,1},{0,0}}, {{1,0},{0,0},{1,0}}, {{1,0},{1,0},{1,0}}, // jkl
  {{1,1},{0,0},{1,0}}, {{1,1},{0,1},{1,0}}, {{1,0},{0,1},{1,0}}, // mno
  {{1,1},{1,0},{1,0}}, {{1,1},{1,1},{1,0}}, {{1,0},{1,1},{1,0}}, // pqr
  {{0,1},{1,0},{1,0}}, {{0,1},{1,1},{1,0}}, {{1,0},{0,0},{1,1}}, // stu
  {{1,0},{1,0},{1,1}}, {{1,0},{0,1},{0,1}}, {{1,1},{0,0},{1,1}}, // vwx
  {{1,1},{0,1},{1,1}}, {{1,0},{0,1},{1,1}}                       // yz
};

void setup() {
  // put your setup code here, to run once:
  
  Serial.begin(9600);
  Serial3.begin(9600);

  for (int i=0; i<3; i++) {
    pinMode(rows[i], OUTPUT);
    digitalWrite(rows[i], HIGH);
  }

  for (int i=0; i<6; i++) {
    pinMode(cols[i], OUTPUT);
    digitalWrite(cols[i], LOW);
  }
}

void display(String str) {

  for (int x=0; x<3; x++) {
    int c = tolower(str[x])-'a';
    
    for (int i=0; i<3; i++){
      for (int j=0; j<2; j++) {
        if (braille[c][i][j]) {
          digitalWrite(cols[j+2*x], HIGH);
          digitalWrite(rows[i], LOW);
          
        } else {
          digitalWrite(cols[j+2*x], LOW);
          digitalWrite(rows[i], HIGH);
          
      }
      delayMicroseconds(900);
    }
  }

}
}
String w;
void loop() {
  // put your main code here, to run repeatedly:


  if(Serial3.available()) {
    w = Serial3.readString();
  }
  delayMicroseconds(1000);
  display(w);
  Serial.println(w);
}
