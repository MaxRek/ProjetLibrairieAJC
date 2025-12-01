package g1.librairie_back.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;

import g1.librairie_back.dto.request.CreateAchatRequest;
import g1.librairie_back.dto.response.AchatResponse;
import g1.librairie_back.model.Achat;
import g1.librairie_back.service.AchatService;
import g1.librairie_back.view.Views;

@RestController
@RequestMapping("/api/achats")
@CrossOrigin("*")
public class AchatRestController {

    @Autowired
    private AchatService achatService;

    @GetMapping
    @JsonView(Views.Common.class)
    public List<AchatResponse> getAll() {
        return achatService.getAll().stream()
                .map(AchatResponse::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @JsonView(Views.Achat.class)
    public AchatResponse getById(@PathVariable Integer id) {
        Achat achat = achatService.getById(id);
        return AchatResponse.convert(achat);
    }

    @PostMapping
    @JsonView(Views.Achat.class)
    public AchatResponse create(@RequestBody CreateAchatRequest dto) {

        Achat achat = achatService.createFromIds(
                dto.getArticleId(),
                dto.getClientId(),
                dto.getQuantiteAchat(),
                dto.getPrix(),
                dto.getDateAchat()
        );

        return AchatResponse.convert(achat);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        achatService.deleteById(id);
    }
}
