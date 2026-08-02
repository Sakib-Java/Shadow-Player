# 🎵 JavaFX Audio Player with Visualizer

A lightweight, modern desktop audio player built with Java and JavaFX. Features smooth audio visualization, custom UI controls, track control and responsive UI animations.

---

https://github.com/user-attachments/assets/6507282e-8ed2-48ed-a0b6-45ace2b63d47

*Real time app usage with bass reactive audio visualization and playback control.*

---

## ✨ Features

- **Playback Controls:** Play, pause, seek, skip forward (+5s), skip backward (-5s).
- **Interactive Time Slider:** Drag the time slider to seek instantly within the current track.
- **Audio Visualizer:** Multi-band FFT audio spectrum visualization synchronized with the playing audio.
- **Volume Control:** Real-time volume adjustments.
- **Looping Mode:** Toggle between stopping at the end of a track or automatically replaying it.

---

## 🛠 Technical Highlights

- **JavaFX UI Control:** utilized functions and listeners to adjust UI animation and behavior for responsiveness.
- **MediaPLayer Library:** properly utilized to control audio through UI buttons and to update Visualizer.
- **Modular MVC Design:** to separate responsibilities for organizing and high maintainability.
- **Thread-safe:** separation between background audio processing and the JavaFX UI thread.
- **Native OS Deployment:** Bundled with a modular JRE via `jpackage` to deliver a standalone, zero-installation `.exe`.

---

## 📥 Download and Use

### Option 1: Download the Release (Recommended)

1. Go to the **Releases** page.
2. Download the latest release.
3. Extract the ZIP archive.
4. **Open audio files with Shadow Player**

You can associate supported audio files with Shadow Player:

1. Right-click an audio file (e.g. `audio.mp3`).
2. Select **Open with** → **Choose another app**.
3. Browse to `Shadow Player.exe`.
4. (Optional) Enable **Always use this app** to make it the default.

Supported formats include: (MPEG, AAC, WAV)

---

## 🛠️ Build from Source

### Prerequisites

- **Java JDK 21** or higher
- **JavaFX SDK**
- **Maven**

### Installing the Repository

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Sakib-Java/Shadow-Player.git
   cd Shadow-Player

2. **Build and Run (Maven):**
   ```Bash
   mvn clean javafx:run
   ```
   (Or import directly into IntelliJ IDEA / Eclipse and run Launcher.java)

---

### 🛠️ Built With
* Java 21 - Core application logic 
* JavaFX 26 - GUI layout and media engine (MediaPlayer, AudioSpectrumListener)
* Maven – Dependency management and project build
* CSS - Custom slider styling and layout themes

---

### 📖 About This Project

Shadow Player is a personal project developed to explore and learn desktop application development with JavaFX. The project focuses on creating a responsive music player that implements real time audio handling with custom UI animations.

---

### 👤 Author

Syed Sakibul Islam Sakib / Shxdes64 - GitHub Profile
