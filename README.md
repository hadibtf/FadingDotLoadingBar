# FadingDotLoadingBar
[![](https://jitpack.io/v/hadibtf/FadingDotLoadingBar.svg)](https://jitpack.io/#hadibtf/FadingDotLoadingBar)
A loading bar with cool move and fade animation.

![demo.gif](https://raw.githubusercontent.com/hadibtf/FadingDotLoadingBar/master/demo.gif "Demo Gif") 

## Installation

Add this repository to your Module build.gradle

```bash
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
And then add this dependency to your Project build.gradle

```bash
dependencies {
    implementation 'com.github.hadibtf:FadingDotLoadingBar:1.0.0'
}
```

## Usage

```Java
//Java
fadingDotLoadingBar.setDotColor(0xff00ff00);
fadingDotLoadingBar.setAnimDuration(1500);
fadingDotLoadingBar.setDotSize(15);
fadingDotLoadingBar.setBackgroundBoxVisibility(true);
fadingDotLoadingBar.setBackgroundBoxBorderRadius(10);
fadingDotLoadingBar.setBackgroundBoxColor(0xff0000ff);
fadingDotLoadingBar.startAnimation();
```

```xml
//XML
<me.bastanfar.fadingdotloading.FadingDotLoadingBar
        app:animDuration="500"
        app:backgroundBoxBorderRadius="70"
        app:backgroundBoxColor="#001998"
        app:backgroundBoxVisibility="true"
        app:dotColor="#FF1376"
        app:dotSize="23"
        app:startAnim="true"/>
```
