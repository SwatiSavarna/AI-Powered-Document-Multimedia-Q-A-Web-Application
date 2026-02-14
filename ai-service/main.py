

from fastapi import FastAPI
from pydantic import BaseModel
import os

from whisper_utils import transcribe_audio
from faiss_utils import store_text, search
from chat_utils import ask_question

app = FastAPI()


UPLOAD_DIR = "/app/uploads"


class ChatRequest(BaseModel):
    question: str



import fitz  
import os

def extract_text_from_pdf(file_path):
    try:
        doc = fitz.open(file_path)
        text = ""
        for page in doc:
            text += page.get_text()
        return text
    except Exception as e:
        return f"Error extracting PDF: {str(e)}"

@app.post("/transcribe/{file_id}")
async def transcribe(file_id: int, request: Request):
    data = await request.json()
    file_path = data.get("filePath")

    if not os.path.exists(file_path):
        return {"error": "File not found"}

    if file_path.lower().endswith(".pdf"):
        print(f"Processing PDF: {file_path}")
        text = extract_text_from_pdf(file_path)
        return {"transcript": text}
    else:
        print(f"Processing Audio/Video: {file_path}")
        # Your existing Whisper code
        result = model.transcribe(file_path)
        return {"transcript": result["text"]}


@app.post("/chat/{file_id}")
def chat(file_id: int, req: ChatRequest):
    answer = ask_question(req.question)
    return {"answer": answer}


@app.get("/summarize/{file_id}")
def summarize(file_id: int):
    docs = search("summary")
    summary = "\n".join(docs[:5])
    return {"summary": summary}









