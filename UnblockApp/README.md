# Unblock App - Android VPN & Finance Education App

A comprehensive Android application that combines VPN functionality with financial literacy education through an interactive 20-step guide system.

## 🚀 Features

### Core Functionality
- **VPN Integration**: Smartproxy VPN service with automatic IP rotation
- **20-Step Finance Guide**: Interactive educational content covering investment basics
- **Quiz System**: Child-friendly quizzes with immediate feedback
- **Share-to-Unlock**: Social sharing mechanism to unlock premium features
- **Modern UI**: Material Design with unique color schemes per page

### Monetization
- **AdMob Integration**: Banner, interstitial, and rewarded video ads
- **Rewarded Ads**: Mandatory viewing after every 5 guide steps
- **Strategic Ad Placement**: Optimized for user engagement and revenue

### Technical Features
- **Firebase Remote Config**: Dynamic feature toggling (VPN/Ads)
- **Navigation Drawer**: Privacy Policy, Rate Us, Share App, Contact Us
- **Responsive Design**: Optimized for all Android screen sizes
- **Security**: Encrypted user input, no data storage

## 📱 App Structure

### Activities
- `SplashActivity`: App launch screen
- `HomeActivity`: Main screen with VPN controls and navigation
- `GuideStep1Activity` to `GuideStep20Activity`: Individual guide pages
- `ShareUnlockActivity`: Social sharing and unlock mechanism

### Key Components
- `SmartproxyVpnService`: VPN connection management
- `AdMobHelper`: Centralized ad management
- `FirebaseRemoteConfigHelper`: Remote configuration handling

## 🛠 Technical Stack

- **Language**: Kotlin
- **Min SDK**: 21 (Android 5.0)
- **Target SDK**: 33
- **Architecture**: Multi-activity with Material Design
- **Dependencies**:
  - Firebase (Remote Config, Analytics)
  - AdMob SDK
  - OneSignal (Push notifications)
  - Material Design Components

## 📋 Setup Instructions

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 21+
- Firebase project setup
- AdMob account
- Smartproxy API credentials

### Installation
1. Clone the repository
```bash
git clone https://github.com/yourusername/unblock-app.git
cd unblock-app
```

2. Open in Android Studio
3. Sync Gradle files
4. Configure Firebase:
   - Add your `google-services.json` file
   - Set up Remote Config parameters
5. Update AdMob IDs in `AdMobHelper.kt`
6. Configure Smartproxy credentials in `SmartproxyVpnService.kt`

### Firebase Remote Config Parameters
```json
{
  "vpn_enabled": true,
  "ads_enabled": true
}
```

### Required Permissions
- `INTERNET`: Network access
- `FOREGROUND_SERVICE`: VPN service
- `ACCESS_NETWORK_STATE`: Network state monitoring
- `BIND_VPN_SERVICE`: VPN functionality

## 🎯 Guide Content Topics

The 20-step guide covers essential financial literacy topics:

1. **VPN Basics**: Understanding Virtual Private Networks
2. **Internet Security**: Protecting your digital assets
3. **VPN Privacy**: Maximizing online privacy
4. **Public Wi-Fi Safety**: Secure browsing practices
5. **Premium Features**: IP rotation and geo-targeting
6. **Investment Management**: Portfolio basics
7. **Stock Market**: Understanding stocks
8. **Investment Types**: Exploring different investments
9. **Diversification**: Portfolio risk management
10. **Risk Management**: Investment protection
11. **Long-term Investing**: Compound growth
12. **Mutual Funds**: Pooled investments
13. **Compound Interest**: The power of compounding
14. **Bonds**: Fixed-income investments
15. **Emergency Funds**: Financial planning essentials
16. **Retirement Planning**: Long-term wealth building
17. **Investment Strategies**: Dollar-cost averaging
18. **Tax-Efficient Investing**: Maximizing returns
19. **Continuous Learning**: Financial education
20. **Taking Action**: Starting your investment journey

## 🎨 UI/UX Features

- **Modern Card Design**: Clean, elevated card layouts
- **Unique Color Schemes**: Different themes for each guide step
- **Interactive Quizzes**: Radio button selections with instant feedback
- **Progress Tracking**: Visual indicators showing completion status
- **Responsive Layouts**: Optimized for various screen sizes

## 💰 Monetization Strategy

- **Rewarded Video Ads**: After steps 5, 10, 15, 20
- **Banner Ads**: Bottom placement on guide pages
- **Interstitial Ads**: App close and final step
- **Native Ads**: Integrated within guide content
- **Share-to-Unlock**: Social engagement for premium access

## 🔧 Configuration

### AdMob Test IDs (Replace with Production IDs)
- Banner: `ca-app-pub-3940256099942544/6300978111`
- Interstitial: `ca-app-pub-3940256099942544/1033173712`
- Rewarded Video: `ca-app-pub-3940256099942544/5224354917`

### Firebase Remote Config
Control app features dynamically without app updates:
- Toggle VPN functionality
- Enable/disable ads
- Update guide content

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 Support

For support and questions:
- Create an issue in this repository
- Contact: [your-email@example.com]

## 🚀 Deployment

### Building Release APK
```bash
./gradlew assembleRelease
```

### Google Play Store Checklist
- [ ] Update app version in `build.gradle`
- [ ] Replace test AdMob IDs with production IDs
- [ ] Configure Smartproxy production credentials
- [ ] Test all functionality thoroughly
- [ ] Prepare store listing materials
- [ ] Submit for review

---

**Built with ❤️ for financial education and digital privacy**
