package ff.cimex.chrifacile.service;

import ff.cimex.chrifacile.request.dto.AnnonceDto;
import ff.cimex.chrifacile.request.dto.FilterDto;
import ff.cimex.chrifacile.entity.Annonce;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnnonceService {
    Annonce addAnnonce(AnnonceDto annonce);
    List<AnnonceDto> getAllAnnonces();
    List<AnnonceDto> getAnnoncesByFilter(FilterDto filtre);
    AnnonceDto getAnnonceById(Long id);
    Page<AnnonceDto> getAllAnnonces(Pageable pageable);
}
