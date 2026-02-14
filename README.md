# AI-Powered Multimedia Q&A Backend

This repository contains the backend services for an AI-powered document and multimedia analysis application. The system allows users to upload PDFs, audio, and video files, transcribes/extracts their content, and allows for interactive Q&A using Large Language Models (LLMs).

## 🚀 Features Implemented
- **Multi-Service Architecture:** Orchestrated using Docker Compose for seamless communication between Java and Python.
- **Intelligent Transcription:** - **PDFs:** Text extraction using `PyMuPDF`.
  - **Audio/Video:** Speech-to-text transcription using `OpenAI Whisper` and `FFmpeg`.
- **AI Chatbot:** Interactive Q&A based on uploaded content via `OpenAI GPT`.
- **Shared Volume Storage:** Synchronized file access between the Spring Boot and FastAPI services.
- **Relational Persistence:** Metadata and transcripts stored in a `MySQL` database.

---

## 🛠️ Tech Stack
| Component | Technology |
| :--- | :--- |
| **Backend Orchestrator** | Java 21, Spring Boot 3.x, Hibernate |
| **AI Processing Service** | Python 3.10, FastAPI, OpenAI Whisper |
| **Database** | MySQL 8.0 |
| **Media Processing** | FFmpeg |
| **Containerization** | Docker, Docker Compose |

---

## ⚙️ Setup & Installation

### 1. Prerequisites
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) installed and running.
- An **OpenAI API Key** with an active billing quota.

### 2. Environment Configuration
Create a `.env` file in the root directory:
```text
OPENAI_API_KEY=your_sk_key_here
