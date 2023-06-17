package eRegistrar.service.impl;

import eRegistrar.model.Block;
import eRegistrar.repository.BlockRepository;
import eRegistrar.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class BlockServiceImpl implements BlockService {

    @Autowired
    BlockRepository blockRepository;

    @Override
    public List<Block> getAllBlocks() {
        return blockRepository.findAll();
    }

    @Override
    public Block saveBlock(Block Block) {
        return blockRepository.save(Block);
    }

    @Override
    public Block getBlockById(Integer BlockId) {
        return (blockRepository.findById(BlockId)).orElse(null);
    }

    @Override
    public void deleteBlockById(Integer BlockId) {
            blockRepository.deleteById(BlockId);
    }

    @Override
    public List<Block> searchBlocks(String searchString) {
       if(isDate(searchString)) {
            return blockRepository.findAllByStartingDateEqualsOrEndingDateEquals(LocalDate.parse(searchString, DateTimeFormatter.ISO_DATE),LocalDate.parse(searchString, DateTimeFormatter.ISO_DATE));
        } else {
           return blockRepository.findByblockNameContaining(searchString);
       }
    }

    private boolean isDate(String searchString) {
        boolean isParseableAsDate = false;
        try {
            LocalDate.parse(searchString, DateTimeFormatter.ISO_DATE);
            isParseableAsDate = true;
        } catch(Exception ex) {
            if(ex instanceof DateTimeParseException) {
                isParseableAsDate = false;
            }
        }
        return isParseableAsDate;
    }
}
