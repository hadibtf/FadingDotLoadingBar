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
        app:backgroundBoxColor="#AFB42B"
        app:backgroundBoxVisibility="true"
        app:dotColor="#7B1FA2"
        app:dotSize="17"
        app:startAnim="true"/>
```
