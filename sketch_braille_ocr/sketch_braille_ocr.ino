# include <ESP8266WiFi.h>
# include <WiFiClient.h>
# include <ESP8266WebServer.h>

const char* ssid = "Be Happy";
const char* password = "anitya123";
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
  Serial.println("HTTP server started");
  Serial.println(myIP);
  pinMode(LED_BUILTIN, OUTPUT);
  digitalWrite(LED_BUILTIN, HIGH);


}

void loop() {
  // put your main code here, to run repeatedly:
  WiFiClient client = server.available();
  if( !client ) {
    digitalWrite(LED_BUILTIN, HIGH);
    return;
  }
  Serial.println("Client Connected");
  digitalWrite(LED_BUILTIN, LOW);

  String request = client.readStringUntil('\n');
  Serial.println(request);
}
