package br.edu.ifba.emprestimos_ms.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.edu.ifba.emprestimos_ms.repository.EmprestimoRepository;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    private boolean mapToBoolean(long count) {
        return count > 0;
    }

    @Transactional(readOnly = true)
    public boolean possuiEmprestimosAtivos(Long usuarioId) {
        return emprestimoRepository.countEmprestimosAtivos(usuarioId) > 0;
    }

    @Transactional(readOnly = true)
    public boolean possuiMultasPendentes(Long usuarioId) {
        return mapToBoolean(emprestimoRepository.countMultasPendentes(usuarioId));
    }

    @Transactional
    public void limparRegistrosDeUsuarioDeletado(Long usuarioId) {
        emprestimoRepository.deleteByUsuarioId(usuarioId);
    }
}
    
    
