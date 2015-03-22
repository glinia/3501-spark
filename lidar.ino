unsigned long pulse_width;

void setup()
{
  Serial.begin(9600);
  pinMode(2, OUTPUT); // trigger pin
  pinMode(3, INPUT); // monitor pin
  pinMode(4, OUTPUT); // control power enable line
  pinMode(5, OUTPUT); // analog out to roboRIO
  digitalWrite(4, HIGH); // turn sensor on
  digitalWrite(2, LOW); // LOW for continuous read
}

void loop()
{
  pulse_width = pulseIn(3, HIGH); // count how long the pulse is high in microseconds

  if (pulse_width != 0) {
    pulse_width /= 10; // 10usec = 1 cm

    if (pulse_width > 255)
      pulse_width = 255;

    analogWrite(5, pulse_width);
  } else { // we're locking up
    digitalWrite(4, LOW); // turn off
    delay(1);
    digitalWrite(4, HIGH); // turn on
    delay(1);
  }

  delay(1); // don't overload the serial port
}

