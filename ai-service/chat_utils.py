from openai import OpenAI
from faiss_utils import search

client = OpenAI()

def ask_question(question: str) -> str:
    context_docs = search(question)
    context = "\n".join(context_docs)

    prompt = f"""
Answer using the context below.
If answer not found, say "Not in document".

Context:
{context}

Question:
{question}
"""

    resp = client.chat.completions.create(
        model="gpt-4o-mini",
        messages=[{"role": "user", "content": prompt}]
    )

    return resp.choices[0].message.content