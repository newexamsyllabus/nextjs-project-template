# Unblock App - Android Studio Project

A complete Android VPN application with guide system, ads integration, and sharing functionality.

## Features

- **VPN Service**: Secure VPN connection using Android VPN API
- **Interactive Guide**: 20-step guide system with quizzes and finance tips
- **Ad Integration**: AdMob banner, interstitial, and rewarded ads
- **Firebase Integration**: Remote configuration for feature toggles
- **Share System**: App sharing functionality to unlock premium features
- **Modern UI**: Material Design with clean, modern interface

## Project Structure

```
UnblockApp/
├── src/main/
│   ├── java/com/example/unblockapp/
│   │   ├── HomeActivity.kt          # Main entry point
│   │   ├── SplashActivity.kt        # Splash screen
│   │   ├── GuideActivity.kt         # Main guide flow
│   │   ├── GuideStep1-20Activity.kt # Individual guide steps
│   │   ├── ShareUnlockActivity.kt   # Share to unlock feature
│   │   ├── SmartproxyVpnService.kt  # VPN service implementation
│   │   ├── AdMobHelper.kt           # Ad management
│   │   └── FirebaseRemoteConfigHelper.kt # Remote config
│   ├── res/
│   │   ├── layout/                  # XML layouts
│   │   ├── drawable/                # UI drawables
│   │   ├── values/                  # Strings, colors, themes
│   │   └── menu/                    # Navigation menu
│   └── AndroidManifest.xml
├── build.gradle                     # App-level build configuration
├── build.gradle.kts                 # Project-level build configuration
├── google-services.json             # Firebase configuration
└── proguard-rules.pro              # ProGuard rules
```

## Setup Instructions

### 1. Prerequisites
- Android Studio Arctic Fox or later
- Android SDK API 21+ (Android 5.0+)
- Firebase project (for remote config and analytics)
- AdMob account (for ads)

### 2. Firebase Setup
1. Create a new Firebase project at https://console.firebase.google.com
2. Add your Android app to the project
3. Download the `google-services.json` file
4. Replace the template `google-services.json` with your actual file
5. Enable Remote Config and Analytics in Firebase console

### 3. AdMob Setup
1. Create an AdMob account at https://admob.google.com
2. Create ad units for:
   - Banner ads
   - Interstitial ads
   - Rewarded ads
3. Update ad unit IDs in the code (currently using test IDs)

### 4. Build and Run
1. Open the project in Android Studio
2. Sync the project with Gradle files
3. Connect an Android device or start an emulator
4. Click "Run" to build and install the app

## Key Components

### VPN Service
- `SmartproxyVpnService.kt`: Implements Android VPN API
- Requires VPN permission from user
- Can be extended to connect to actual proxy servers

### Guide System
- 20 interactive steps with quizzes
- Finance tips integrated into each step
- Progress tracking and validation
- Ad integration between steps

### Ad Integration
- Banner ads on guide pages
- Interstitial ads after guide completion
- Rewarded ads every 5 steps
- Test ad unit IDs included (replace with production IDs)

### Remote Configuration
- VPN enable/disable toggle
- Ads enable/disable toggle
- Configurable via Firebase Remote Config

## Permissions Required

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.BIND_VPN_SERVICE" />
```

## Dependencies

- **AndroidX**: Core Android libraries
- **Material Design**: UI components
- **Firebase**: Remote Config and Analytics
- **AdMob**: Advertisement integration
- **OneSignal**: Push notifications (optional)

## Customization

### Changing App Theme
Edit `res/values/themes.xml` and `res/values/colors.xml`

### Adding More Guide Steps
1. Create new `GuideStepXActivity.kt` files
2. Add corresponding layout files
3. Update `AndroidManifest.xml`
4. Update navigation in `GuideActivity.kt`

### VPN Configuration
Modify `SmartproxyVpnService.kt` to connect to your VPN servers

### Ad Unit IDs
Replace test ad unit IDs in:
- `AdMobHelper.kt`
- Layout files with AdView components

## Testing

### Test Ad Unit IDs (Already Included)
- Banner: `ca-app-pub-3940256099942544/6300978111`
- Interstitial: `ca-app-pub-3940256099942544/1033173712`
- Rewarded: `ca-app-pub-3940256099942544/5224354917`

### VPN Testing
- Test on physical device (VPN doesn't work in emulator)
- Grant VPN permission when prompted
- Check VPN status in device settings

## Production Deployment

1. **Replace test ad unit IDs** with production IDs
2. **Update Firebase configuration** with production project
3. **Configure ProGuard** rules for release builds
4. **Test VPN functionality** thoroughly
5. **Update app signing** configuration
6. **Submit to Google Play Store**

## Security Notes

- VPN service requires careful implementation for security
- User data should be encrypted
- Follow Android security best practices
- Regular security audits recommended

## Support

For issues and questions:
1. Check Android Studio logs
2. Verify Firebase and AdMob configurations
3. Test on different devices and Android versions
4. Review permissions and manifest settings

## License

This project is for educational and development purposes. Ensure compliance with:
- Google Play Store policies
- VPN service regulations in your region
- AdMob policies
- Firebase terms of service
