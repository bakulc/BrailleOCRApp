# include <ESP8266WiFi.h>
# include <WiFiClient.h>
# include <ESP8266WebServer.h>

const char* ssid = "Braille OCR";
const char* password = "12345678  ";
WiFiServer server(80);


void setup() {
  // put your setup code here, to run once:
  
  Serial.begin(9600);
  Serial.println();
  //WiFi.mode(WIFI_STA); //to hide the ESP visible as wifi network
  WiFi.mode(WIFI_AP); 
  WiFi.softAP(ssid, password);
  IPAddress myIP = WiFi.softAPIP(); //Get IP address
  server.begin();
  //Serial.println("HTTP server started");
  //Serial.println(myIP);
}


void loop() {
  // put your main code here, to run repeatedly:
  WiFiClient client = server.available();

  if( !client ) {
    return;
  }

  String request = client.readStringUntil('\n');
  request.remove(0, 5);
  Serial.println(request);
}
