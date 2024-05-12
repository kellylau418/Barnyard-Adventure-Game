import controlP5.*;
ControlP5 cp5;

controlP5.Toggle dayNightToggle;
controlP5.Toggle seasonToggle;
controlP5.Slider textSlider;
controlP5.Knob cowKnob;
controlP5.Knob pigKnob;
controlP5.Knob sheepKnob;
controlP5.Button submitValueButton;
controlP5.Textlabel valueText;
controlP5.Textlabel soundQuestionLabel;
controlP5.Textlabel animalSoundLabel;
controlP5.Textfield soundAnswer;
controlP5.Button submitSound;
controlP5.Button nextSoundButton;


PFont ubuntuFont;
PFont medUbuntuFont;
PFont chickenFont;
PFont smallChickenFont;

PImage autumnDayImg, autumnNightImg, summerDayImg, summerNightImg;
PImage currentBackground;

PImage cowImg, pigImg, sheepImg;
PImage littleBlueTruckImg, dumpTruckImg, mudImg;

boolean theday;
boolean theseason;

color lightblue = color(108, 194, 255);
color darkblue = color(16, 81, 127);
color midnight = color(40, 35, 74);
color yellow = color(252, 186, 3);
color green = color(45, 122, 65);
color orange = color(255,69,0);

String[] storyTexts = {"Once upon a time, there was a little blue truck driving down the farm roads.","It greeted all the animals with a friendly ‘Beep’.","But what did the animals say in return?", "The sheep said 'Baaa!' The pig said 'Oink!'", "The cow said 'Mooo!', The horse said, 'Neighhh!'", "Suddenly… 'HONKK!' yelled a dump truck,", "'ROOMM!' and the truck got stuck in the mud!", "The little blue truck tried to help, but it got stuck too!","'Beep beep beep, help help help!!' yelled the little blue truck.", "All the animals heard and came to the rescue!", "With a big push, everyone got out of the mud!"};
String currentStoryText = storyTexts[5];
color currentTextColor = color(0); 
color currenttextColor = color(0);


color currentBackgroundColor = color(0);


int dumpTruckX = -700;
int dumpTruckSpeed = 6;
int littleBlueTruckX = -150;
int littleBlueTruckSpeed = 4;


int cow1X = -140;
int cow1Speed = 0;
int cow2X = -140;
int cow2Speed = 0;
int cow3X = -140;
int cow3Speed = 0;
int cow4X = -140;
int cow4Speed = 0;

int pig1X = -120;
int pig1Speed = 0;
int pig2X = -120;
int pig2Speed = 0;
int pig3X = -120;
int pig3Speed = 0;
int pig4X = -120;
int pig4Speed = 0;

int sheep1X = -100;
int sheep1Speed =0;
int sheep2X = -100;
int sheep2Speed =0;
int sheep3X = -100;
int sheep3Speed =0;
int sheep4X = -100;
int sheep4Speed =0;

String[] soundQuestionList = {"What sound does a sheep make?", "What sound does a cow make?", "What sound does a pig make?"};
String[] soundAnswerList = {"Baaa!", "Mooo!", "Oink!"}; 
String currentQuestion = soundQuestionList[2];
String currentAnswer = soundAnswerList[2];
PImage[] animalList = new PImage[3]; 
PImage currentAnimal;

String checkAnimaltext;
int index; 

void setup(){
  size(1700,680);
  
  summerDayImg = loadImage("summerDayImg.png");
  summerNightImg = loadImage("summerNightImg.png");
  autumnDayImg = loadImage("autumnDayImg.png");
  autumnNightImg = loadImage("autumnNightImg.png");
  currentBackground = summerDayImg;
  
  cowImg = loadImage("cowImg.png");
  pigImg = loadImage("pigImg.png");
  sheepImg = loadImage("sheepImg.png");
  littleBlueTruckImg = loadImage("littleBlueTruck.png");
  dumpTruckImg = loadImage("dumpTruck.png");
  mudImg = loadImage("mudImg.png");

  ubuntuFont = createFont("Ubuntu copy.ttf",20);
  medUbuntuFont = createFont("Ubuntu copy.ttf",30);
  chickenFont = createFont("chickenpie.ttf",20);
  smallChickenFont = createFont("chickenpie.ttf",15);
  
  animalList[0] = loadImage("sheepImg.png");
  animalList[1] = loadImage("cowImg.png");
  animalList[2] = loadImage("pigImg.png");
  currentAnimal = animalList[2];
  
  
  cp5 = new ControlP5(this);
  
  dayNightToggle = cp5.addToggle("switchDayNight")
                .setPosition(70,40)
                .setSize(100,50)
                .setValue(true)
                .setMode(ControlP5.SWITCH)
                .setColorCaptionLabel(color(0))
                .setCaptionLabel("day or night")
                .setFont(chickenFont)
                .setColorBackground(yellow)
                ;
  seasonToggle = cp5.addToggle("switchSeason")
                .setPosition(210,40)
                .setSize(100,50)
                .setValue(true)
                .setMode(ControlP5.SWITCH)
                .setColorCaptionLabel(color(0))
                .setCaptionLabel("summer or autumn")
                .setFont(chickenFont)
                .setColorBackground(green)
                ;
  textSlider = cp5.addSlider("storyTextSlider")
                .setPosition(600,50)
                .setSize(680,60)
                .setCaptionLabel("Slide For Story")
                .setFont(chickenFont)
                .setRange(0,10)
                .setValue(0)
                .setSliderMode(0)
                
                .setNumberOfTickMarks(11)
                .setColorCaptionLabel(color(0))
                .setColorBackground(color(0))
                .snapToTickMarks(true)
                ;
                  
  Label textLabel = textSlider.getCaptionLabel();
  textLabel.align(ControlP5.LEFT, ControlP5.TOP_OUTSIDE);
  textLabel.setPaddingY(10);
  
  Label textValueLabel = textSlider.getValueLabel();
  textValueLabel.setVisible(false); 
  
  cowKnob = cp5.addKnob("changeCowNumber")
                .setSize(110,110)
                .setPosition(960,200)
                .setColorBackground(currentBackgroundColor)
                .setRange(0,4)
                .setValue(0)
                .setDragDirection(Knob.VERTICAL)
                .setStartAngle(radians(270))
                .setAngleRange(radians(360))
                
                .setNumberOfTickMarks(4)
                .snapToTickMarks(true)
                
                .setColorCaptionLabel(currenttextColor)
                .setFont(smallChickenFont)
                .setCaptionLabel("Number Of Cows")
                ;  
  sheepKnob = cp5.addKnob("changeSheepNumber")
                .setSize(110,110)
                .setPosition(1110,200)
                .setColorBackground(currentBackgroundColor)

                .setRange(0,4)
                .setValue(0)
                .setDragDirection(Knob.VERTICAL)
                .setStartAngle(radians(270))
                .setAngleRange(radians(360))
                
                .setNumberOfTickMarks(4)
                .snapToTickMarks(true)
                
                .setColorCaptionLabel(currenttextColor)
                .setFont(smallChickenFont)
                .setCaptionLabel("Number Of Sheeps")
                ;  
                
  pigKnob = cp5.addKnob("changePigNumber")
                .setSize(110,110)
                .setPosition(1260,200)
                .setColorBackground(currentBackgroundColor)

                .setRange(0,4)
                .setValue(0)
                .setDragDirection(Knob.VERTICAL)
                .setStartAngle(radians(270))
                .setAngleRange(radians(360))
                
                .setNumberOfTickMarks(4)
                .snapToTickMarks(true)
                
                .setColorCaptionLabel(currenttextColor)
                .setFont(smallChickenFont)
                .setCaptionLabel("Number Of Pigs")
                ;  
                
  submitValueButton = cp5.addButton("checkAnimalNumber")
                .setPosition(1120,350)
                .setSize(90,50)
                .setCaptionLabel("check")
                .setFont(smallChickenFont)
                .setColorBackground(currentBackgroundColor)

                ;
                
  valueText = cp5.addTextlabel("valueResponse")
                .setPosition(950,430)
                .setText("")
                .setColor(currenttextColor)
                .setFont(chickenFont)
                ;
                
  animalSoundLabel = cp5.addTextlabel("animalSound")
                .setPosition(520,380)
                .setColor(currenttextColor)
                .setText("")
                .setFont(chickenFont)
                ;
                
  nextSoundButton = cp5.addButton("nextQuestion")
                .setPosition(200,350)
                .setColorBackground(currentBackgroundColor)

                .setSize(90,50)
                .setCaptionLabel("Next")
                .setFont(chickenFont)
                ;

  submitSound = cp5.addButton("checkSoundAnswer")
                .setPosition(70,350)
                .setColorBackground(currentBackgroundColor)

                .setSize(90,50)
                .setCaptionLabel("Check")
                .setFont(chickenFont)
                ;
                
                
  soundAnswer = cp5.addTextfield("soundTextField")
                .setPosition(70,250)
                .setSize(320,65)
                .setColorBackground(currentBackgroundColor)

                .setColorCaptionLabel(currenttextColor)
                .setCaptionLabel("")
                .setFont(chickenFont)
                ;
                
}

void draw(){
  background(currentBackground);
  background();
  
  textFont(chickenFont);
  fill(currentTextColor);
  text(currentStoryText,600,160);
  
  image(mudImg,980,540,490,180);
  
  animalAnimations();
  
  textFont(chickenFont);
  fill(currentTextColor);
  text(currentQuestion,70,230);
  
  image(currentAnimal,400,400,120,80);

}
  

void animalAnimations(){
  image(dumpTruckImg,dumpTruckX,470,220,173);
  dumpTruckX += dumpTruckSpeed;
  
  image(littleBlueTruckImg,littleBlueTruckX,560,180,110);
  littleBlueTruckX += littleBlueTruckSpeed;
  
  if (dumpTruckX > 1175 && dumpTruckSpeed == 6){
    dumpTruckSpeed = 0;
  }
  
  if (littleBlueTruckX > 1047 && littleBlueTruckSpeed == 4){
    littleBlueTruckSpeed = 0;
  }
  
  cowAnimations();

  pigAnimations();
  
  sheepAnimations();
}

void cowAnimations(){
  image(cowImg,cow1X,572,140,80);
  cow1X += cow1Speed;
  if (cow1X > 900 && cow1Speed == 3){
    cow1Speed = 0;
  }
  
  image(cowImg,cow2X,570,140,80);
  cow2X += cow2Speed;
  if (cow2X > 860 && cow2Speed == 3){
    cow2Speed = 0;
  }
  
  image(cowImg,cow3X,590,140,80);
  cow3X += cow3Speed;
  if (cow3X > 960 && cow3Speed == 3){
    cow3Speed = 0;
  }
  image(cowImg,cow4X,582,140,80);
  cow4X += cow4Speed;
  if (cow4X > 890 && cow4Speed == 3){
    cow4Speed = 0;
  }
}

void pigAnimations(){
  image(pigImg,pig1X,570,120,70);
  pig1X += pig1Speed;
  if (pig1X > 900 && pig1Speed == 3){
    pig1Speed =0;
  }
  
  image(pigImg,pig2X,565,120,70);
  pig2X += pig2Speed;
  if (pig2X > 870 && pig2Speed == 3){
    pig2Speed =0;
  }
  
  image(pigImg,pig3X,575,120,70);
  pig3X += pig3Speed;
  if (pig3X > 855 && pig3Speed == 3){
    pig3Speed =0;
  }
  
  image(pigImg,pig4X,568,120,70);
  pig4X += pig4Speed;
  if (pig4X > 955 && pig4Speed == 3){
    pig4Speed = 0;
  }
  
}
  

void sheepAnimations(){
  image(sheepImg,sheep1X,580,100,80);
  sheep1X += sheep1Speed;
  if (sheep1X > 840 && sheep1Speed == 3){
    sheep1Speed = 0;
  }
  
  image(sheepImg,sheep2X,575,100,80);
  sheep2X += sheep2Speed;
  if (sheep2X > 940 && sheep2Speed == 3){
    sheep2Speed = 0;
  }
  
  image(sheepImg,sheep3X,585,100,80);
  sheep3X += sheep3Speed;
  if (sheep3X > 875 && sheep3Speed == 3){
    sheep3Speed = 0;
  }
  
  image(sheepImg,sheep4X,590,100,80);
  sheep4X += sheep4Speed;
  if (sheep4X > 960 && sheep4Speed == 3){
    sheep4Speed = 0;
  }
  
}

void changeCowNumber (int value){
  if (value == 0){
    cowValue0();
  }
  
  if (value == 1){
    cowValue1();
  }
  
  if (value == 2){
    cowValue2();
  }
  
  if (value == 3){
    cowValue3();
  }
  
  if (value == 4){
    cowValue4();
  }    
}

void cowValue0(){
  cow1X = -140;
  cow1Speed = 0;
  cow2X = -140;
  cow2Speed = 0;
  cow3X = -140;
  cow3Speed = 0;
  cow4X = -140;
  cow4Speed = 0;
}

void cowValue1(){
  cow1Speed = 3;  
  cow2X = -140;
  cow2Speed = 0;
  cow3X = -140;
  cow3Speed = 0;
  cow4X = -140;
  cow4Speed = 0;
}

void cowValue2(){
  cow2Speed = 3;
  cow3X = -140;
  cow3Speed = 0;
  cow4X = -140;
  cow4Speed = 0;  
}

void cowValue3(){
  cow3Speed = 3;
  cow4X = -140;
  cow4Speed = 0;
}

void cowValue4(){
  cow4Speed = 3;
}

void changePigNumber (int value){
  if (value == 0){
    pigValue0();
  }
  
  if (value == 1){
    pigValue1();
  }
  
  if (value == 2){
    pigValue2();
  }
  
  if (value == 3){
    pigValue3();
  }
  
  if (value == 4){
     pigValue4();
  }
}

void pigValue0(){
  pig1X = -120;
  pig1Speed = 0;
  pig2X = -120;
  pig2Speed = 0;
  pig3X = -120;
  pig3Speed = 0;
  pig4X = -120;
  pig4Speed = 0;
}

void pigValue1(){
  pig1Speed = 3;
  pig2X = -120;
  pig2Speed = 0;
  pig3X = -120;
  pig3Speed = 0;
  pig4X = -120;
  pig4Speed = 0;
}

void pigValue2(){
  pig2Speed = 3;
  pig3X = -120;
  pig3Speed = 0;
  pig4X = -120;
  pig4Speed = 0;
}

void pigValue3(){
  pig3Speed = 3;
  pig4X = -120;
  pig4Speed = 0;
}

void pigValue4(){
  pig4Speed = 3;
}

void changeSheepNumber(int value){
  if (value ==0){
    sheepValue0();
  }
  
  if (value == 1){
    sheepValue1();
  }
  
  if (value == 2){
    sheepValue2();
  }
  
  if (value == 3){
    sheepValue3();
  }
  
  if (value == 4){
    sheepValue4();
  }
}

void sheepValue0(){
  sheep1X = -100;
  sheep1Speed =0;
  sheep2X = -100;
  sheep2Speed =0;
  sheep3X = -100;
  sheep3Speed =0;
  sheep4X = -100;
  sheep4Speed =0;
}

void sheepValue1(){
  sheep1Speed = 3;
  sheep2X = -100;
  sheep2Speed =0;
  sheep3X = -100;
  sheep3Speed =0;
  sheep4X = -100;
  sheep4Speed =0;
}

void sheepValue2(){
  sheep2Speed = 3;
  sheep3X = -100;
  sheep3Speed =0;
  sheep4X = -100;
  sheep4Speed =0;
}

void sheepValue3(){
  sheep3Speed = 3;
  sheep4X = -100;
  sheep4Speed =0;
}

void sheepValue4(){
  sheep4Speed = 3;
}


void checkAnimalNumber(){
  float userCowValue = cowKnob.getValue();
  float userPigValue = pigKnob.getValue();
  float userSheepValue = sheepKnob.getValue();
  if ( userCowValue > 2 && userPigValue > 2 && userSheepValue > 2 ){
    valueText.setColor(currenttextColor);
    valueText.setText("Good Job! The animals are strong enough!"); 
    if (dumpTruckX > 1175 && dumpTruckSpeed == 0){
      dumpTruckSpeed = 2;
    }
    
    if (littleBlueTruckX > 1047 && littleBlueTruckSpeed == 0){
      littleBlueTruckSpeed = 2;
    }
        
    if (cow1X > 900 && cow1Speed == 0){
      cow1Speed = 2;
    }
    
    if (cow2X > 860 && cow2Speed == 0){   
      cow2Speed = 2;
    }
    
    if (cow3X > 960 && cow3Speed == 0){
      cow3Speed = 2;
    }
    
    if (cow4X > 890 && cow4Speed == 0){
      cow4Speed = 2;
    }
    
    if (pig1X > 900 && pig1Speed == 0){
      pig1Speed = 2;
    }
    
    if (pig2X > 870 && pig2Speed == 0){
      pig2Speed = 2;
    }
    
    if (pig3X > 855 && pig3Speed == 0){
      pig3Speed = 2;
    }
    
    if (pig4X > 955 && pig4Speed == 0){
      pig4Speed = 2;
    }

    if (sheep1X > 840 && sheep1Speed == 0){      
      sheep1Speed =2;
    }
    
    if (sheep2X > 940 && sheep2Speed == 0){         
      sheep2Speed =2;
    }

    if (sheep3X > 875 && sheep3Speed == 0){      
      sheep3Speed =2;
    }
    
    if (sheep4X > 960 && sheep4Speed == 0){      
    sheep4Speed =2;  
    }
  }
  
  else{
      valueText.setColor(currenttextColor);
      valueText.setText("The animals aren't strong enough, try again.");
  }    
}
  

void switchDayNight(boolean theDay){
  theday = theDay;
}

void switchSeason(boolean theSeason){
  theseason = theSeason;
}

void background(){
  if (theday == true && theseason == true){
    currentBackground = summerDayImg;
    dayNightToggle.setColorBackground(yellow);
    seasonToggle.setColorBackground(green);
    
    textSlider.setColorCaptionLabel(color(0));
    currentTextColor = color(0);
    currenttextColor = color(0);

    soundAnswer.setColorCaptionLabel(currentTextColor);
    animalSoundLabel.setColor(currenttextColor);
    cowKnob.setColorCaptionLabel(currenttextColor);
    sheepKnob.setColorCaptionLabel(currenttextColor);
    pigKnob.setColorCaptionLabel(currenttextColor);
  }
  
  else if (theday == true && theseason == false){
    currentBackground = autumnDayImg;
    dayNightToggle.setColorBackground(yellow);
    seasonToggle.setColorBackground(orange);
    
    textSlider.setColorCaptionLabel(color(0));
    currentTextColor = color(0);
    currenttextColor = color(0);

    soundAnswer.setColorCaptionLabel(currentTextColor);
    animalSoundLabel.setColor(currenttextColor);
    cowKnob.setColorCaptionLabel(currenttextColor);
    sheepKnob.setColorCaptionLabel(currenttextColor);
    pigKnob.setColorCaptionLabel(currenttextColor);
  }
  
  else if (theday == false && theseason == true){
    currentBackground = summerNightImg;
    dayNightToggle.setColorBackground(darkblue);
    dayNightToggle.setColorCaptionLabel(color(255));
    seasonToggle.setColorCaptionLabel(color(255));
    seasonToggle.setColorBackground(green);
    
    textSlider.setColorCaptionLabel(color(200));
    currentTextColor = color(200);
    currenttextColor = color(255);
    
    soundAnswer.setColorCaptionLabel(currentTextColor);    
    animalSoundLabel.setColor(currenttextColor);
    cowKnob.setColorCaptionLabel(currenttextColor);
    sheepKnob.setColorCaptionLabel(currenttextColor);
    pigKnob.setColorCaptionLabel(currenttextColor);
    

  }
  
  else if (theday == false && theseason == false){
    currentBackground = autumnNightImg;
    dayNightToggle.setColorBackground(darkblue);
    seasonToggle.setColorBackground(orange);
    dayNightToggle.setColorCaptionLabel(color(255));
    seasonToggle.setColorCaptionLabel(color(255));
    
    animalSoundLabel.setColor(currenttextColor);
    textSlider.setColorCaptionLabel(color(200));
    currentTextColor = color(200);
    currenttextColor = color(255);
    
    soundAnswer.setColorCaptionLabel(currentTextColor);
    cowKnob.setColorCaptionLabel(currenttextColor);
    sheepKnob.setColorCaptionLabel(currenttextColor);
    pigKnob.setColorCaptionLabel(currenttextColor);
  }

}
    
void storyTextSlider(int value){
  int story = value;
  currentStoryText = storyTexts[story];
}

void nextQuestion(){
  index = int (random(0,soundQuestionList.length));
  currentQuestion = soundQuestionList[index];
  currentAnswer = soundAnswerList[index];
  currentAnimal = animalList[index];
  soundAnswer.setCaptionLabel("");
  soundAnswer.setText("");
  animalSoundLabel.setText("");
}


void checkSoundAnswer(){
  String userSoundAnswer = soundAnswer.getText();
  if (userSoundAnswer.equals(currentAnswer)){
    soundAnswer.setColorCaptionLabel(currenttextColor);
    soundAnswer.setCaptionLabel("Correct");
    String sound = soundAnswer.getText();
    if (sound.equals("Oink!")){ 
      animalSoundLabel.setText("Oink!");
    }
    
    else if(sound.equals("Baaa!")){
      animalSoundLabel.setText("Baaa!");
    }
    
    else if (sound.equals("Mooo!")){
      animalSoundLabel.setText("Mooo!");
    }    
  }
  
  else{
    soundAnswer.setColorCaptionLabel(currenttextColor);
    soundAnswer.setCaptionLabel("Incorrect, try again.");
    soundAnswer.setText("");
  }
}
  
