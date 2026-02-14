

import faiss
import numpy as np
from openai import OpenAI
import os



client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))

dimension = 1536
index = faiss.IndexFlatL2(dimension)
documents = []

def get_embedding(text: str):
    """Helper to get 1536-dim vector from OpenAI"""
    emb = client.embeddings.create(
        model="text-embedding-3-small",
        input=text
    )
    return np.array(emb.data[0].embedding, dtype="float32")

def store_text(file_id: int, text: str):
    """Matches the call in main.py: store_text(file_id, text)"""
    global documents
    vec = get_embedding(text)
    index.add(np.array([vec]))
    
    documents.append(text)

def search(query: str, k: int = 3):
    if index.ntotal == 0:
        return []
    
    qvec = get_embedding(query)
    D, I = index.search(np.array([qvec]), k)
    
  
    return [documents[i] for i in I[0] if i != -1 and i < len(documents)]








