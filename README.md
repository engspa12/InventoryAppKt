# InventoryAppKt

#### InventoryAppKt is an Android application that permits users to build their own inventory of products for sale. A thumbnail of the product can be stored along with some extra information such as its name, brand, price, quantity, year of manufacture, and so on. Users can also delete products at any given time by just swiping the items on the main screen or by tapping on a button on the product details screen. An explanation message guides the user in case of an empty inventory.

#### InventoryAppKt brings together several Android Architecture Components such as Room, Navigation and ViewModels.

<img src="https://images2.imgbox.com/99/a3/mmsDU0Cs_o.png" width="375" height="725"><img height="725" hspace="20"/><img src="https://images2.imgbox.com/0a/9e/bsHasAOm_o.png" width="375" height="725">
<img width="770" vspace="20"/>
<img src="https://images2.imgbox.com/91/ae/VhudpOGb_o.png" width="375" height="725"><img height="725" hspace="20"/><img src="https://images2.imgbox.com/04/52/Cyus9oZA_o.png" width="375" height="725">
<img width="770" vspace="20"/>
<img src="https://images2.imgbox.com/c6/c8/wL8xCeQn_o.png" width="375" height="725"><img height="725" hspace="20"/><img src="https://images2.imgbox.com/e8/f3/nic8WMK3_o.png" width="375" height="725">
<img width="770" vspace="20"/>

## Getting Started

#### These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisites

#### InventoryAppKt was developed using Android Studio IDE, so you must install it on your computer before proceeding:

###### https://developer.android.com/studio/

## Next Steps

#### You can proceed to clone the project to your local machine, but DO NOT enter Android Studio yet. First, you need to set up your Firebase project as indicated in the next paragraph.

#### InventoryAppKt requires Firebase Storage for uploading images and Firebase Authentication for security reasons (although the app uses an anonymous sign-in method). Therefore, in order to use InventoryAppKt, you need to set up a project in the Firebase console and then add Firebase to your Android app by clicking on the corresponding button in the Project Overview section. This last part involves that you include the required data of your local machine such as the project package name and the SHA-1 fingerprint certificate. For further information, check this link:

###### https://firebase.google.com/docs/android/setup

#### Once you have your project ready, you must add Firebase to your Android app. Remember to download the **_google-services.json_** file and move it to the app directory (into the app module). The Firebase platform will ask you to run the app so it can confirm a successful communication. Therefore, open Android Studio, build the project and run it. DO NOT try to insert items yet. Ignore the Authentication Failed toast message displayed and proceed to the next step.

#### If the communication is successful, uninstall the app and go to the Authentication section in the Firebase console to enable anonymous sign-in method.

#### Lastly, go to the Storage section in the Firebase console and create a folder named: **inventory_photos**.

#### Reinstall the app and start using it on your Android device.

## Compatibility

#### Minimum Android SDK: InventoryAppKt requires a minimum API level of 21.
#### Compile Android SDK: InventoryAppKt requires you to compile against API 32 or later.

## Getting Help

#### To report a specific problem or feature request, feel free to open an issue on Github. For questions, suggestions, or anything else, email to:

###### arturo.lpc12@gmail.com

## Author

#### Daniel Bedoya - @engspa12 on GitHub

## License

#### See the LICENSE file for details.
