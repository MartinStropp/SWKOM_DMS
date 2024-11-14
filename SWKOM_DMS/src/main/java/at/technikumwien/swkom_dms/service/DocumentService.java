package at.technikumwien.swkom_dms.service;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public String saveDocument(MultipartFile file) throws IOException {
        Document document = new Document();
        document.setFileName(file.getOriginalFilename());
        document.setData(file.getBytes());

        documentRepository.save(document);
        return "File uploaded successfully";
    }

    public List<String> getAllDocumentNames() {
        return documentRepository.findAll()
                .stream()
                .map(Document::getFileName)
                .collect(Collectors.toList());
    }
}
