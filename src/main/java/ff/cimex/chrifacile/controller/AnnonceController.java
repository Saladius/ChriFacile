package ff.cimex.chrifacile.controller;

import ff.cimex.chrifacile.dto.AnnonceDto;
import ff.cimex.chrifacile.dto.FilterDto;
import ff.cimex.chrifacile.entity.Annonce;
import ff.cimex.chrifacile.service.AnnonceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/annonce")
public class AnnonceController {

    private final AnnonceService annonceService;

    @GetMapping("/{id}")
    public ResponseEntity<AnnonceDto> getAnnonceById(@PathVariable Long id) {

        return new ResponseEntity<>(annonceService.getAnnonceById(id),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<AnnonceDto> addAnnonce(@RequestBody AnnonceDto annonceDto) {
        Annonce addedAnnonce = annonceService.addAnnonce(annonceDto);
        if (Objects.nonNull(addedAnnonce)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/filter")
    public ResponseEntity<List<AnnonceDto>> getAnnoncesByFilter(@RequestBody FilterDto filter) {
        try {
            List<AnnonceDto> result = annonceService.getAnnoncesByFilter(filter);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
