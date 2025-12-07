// File upload handling
const fileUploadArea = document.getElementById('fileUploadArea');
const fileInput = document.getElementById('imagem');

fileUploadArea.addEventListener('click', () => {
    fileInput.click();
});

fileUploadArea.addEventListener('dragover', (e) => {
    e.preventDefault();
    fileUploadArea.style.backgroundColor = '#1a1a1a';
});

fileUploadArea.addEventListener('dragleave', () => {
    fileUploadArea.style.backgroundColor = '#0a0a0a';
});

fileUploadArea.addEventListener('drop', (e) => {
    e.preventDefault();
    fileUploadArea.style.backgroundColor = '#0a0a0a';
    const files = e.dataTransfer.files;
    if (files.length > 0) {
        fileInput.files = files;
        updateFileUploadText(files[0].name);
    }
});

fileInput.addEventListener('change', (e) => {
    if (e.target.files.length > 0) {
        updateFileUploadText(e.target.files[0].name);
    }
});

function updateFileUploadText(fileName) {
    const p = fileUploadArea.querySelector('p');
    p.textContent = `Arquivo selecionado: ${fileName}`;
    p.style.color = '#ff8c5a';
}

// Form submission
document.getElementById('monumentoForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const formData = new FormData(e.target);
    const data = {
        nome: formData.get('nome'),
        endereco: formData.get('endereco'),
        artista: formData.get('artista'),
        descricao: formData.get('descricao'),
        decreto: formData.get('decreto') || '',
        idLocalidade: formData.get('idLocalidade')
    };

    // Handle image file if selected
    const imagem = formData.get('imagem');
    if (imagem && imagem.size > 0) {
        // For now, we'll just note that image was selected
        // In a full implementation, you'd upload it separately or convert to base64
        console.log('Imagem selecionada:', imagem.name);
    }

    try {
        const response = await fetch('http://localhost:8080/api/monumentos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        });

        const result = await response.json();
        
        if (response.ok) {
            showMessage('Monumento cadastrado com sucesso!', 'success');
            resetForm();
        } else {
            showMessage('Erro ao cadastrar monumento: ' + (result.message || 'Erro desconhecido'), 'error');
        }
    } catch (error) {
        console.error('Erro:', error);
        showMessage('Erro ao conectar com o servidor. Verifique se o backend está rodando.', 'error');
    }
});

function resetForm() {
    document.getElementById('monumentoForm').reset();
    const p = fileUploadArea.querySelector('p');
    p.textContent = 'Clique ou arraste uma imagem aqui';
    p.style.color = '#ff6b35';
    hideMessage();
}

function showMessage(text, type) {
    const messageDiv = document.getElementById('message');
    messageDiv.textContent = text;
    messageDiv.className = `message ${type}`;
    
    setTimeout(() => {
        hideMessage();
    }, 5000);
}

function hideMessage() {
    const messageDiv = document.getElementById('message');
    messageDiv.className = 'message';
    messageDiv.style.display = 'none';
}

