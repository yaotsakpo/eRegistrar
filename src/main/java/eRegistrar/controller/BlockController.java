package eRegistrar.controller;

import eRegistrar.model.Block;
import eRegistrar.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BlockController {

    @Autowired
    private BlockService BlockService;

    @GetMapping(value = {"/eRegistrar/Block/list","/Block/list"})
    public ModelAndView listBlocks() {
        ModelAndView modelAndView = new ModelAndView();
        List<Block> Blocks = BlockService.getAllBlocks();
        modelAndView.addObject("Blocks", Blocks);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("BlocksCount", Blocks.size());
        modelAndView.setViewName("Block/list");
        return modelAndView;
    }

    @GetMapping(value = {"/eRegistrar/Block/new","/Block/new"})
    public String displayNewBlockForm(Model model) {
        model.addAttribute("Block", new Block());
        return "Block/new";
    }

    @PostMapping(value = {"/eRegistrar/Block/new","/Block/new"})
    public String addNewBlock(@Valid @ModelAttribute("Block") Block Block,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "Block/new";
        }
        Block = BlockService.saveBlock(Block);
        return "redirect:/eRegistrar/Block/list";
    }

    @GetMapping(value = {"/eRegistrar/Block/edit/{BlockId}","/Block/edit/{BlockId}"})
    public String editBlock(@PathVariable Integer BlockId, Model model) {
        Block Block = BlockService.getBlockById(BlockId);
        if (Block != null) {
            model.addAttribute("Block", Block);
            return "Block/edit";
        } else {
            // TODO
        }
        return "Block/list";
    }

    @PostMapping(value = {"/eRegistrar/Block/edit","/Block/edit"})
    public String updateBlock(@Valid @ModelAttribute("Block") Block Block,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "Block/edit";
        }
        Block = BlockService.saveBlock(Block);
        return "redirect:/eRegistrar/Block/list";
    }

    @GetMapping(value = {"/eRegistrar/Block/delete/{BlockId}","/Block/delete/{BlockId}"})
    public String deleteBlock(@PathVariable Integer BlockId, Model model) {
        BlockService.deleteBlockById(BlockId);
        return "redirect:/eRegistrar/Block/list";
    }

    @GetMapping(value = {"/eRegistrar/Block/search", "/Block/search"})
    public ModelAndView searchBlocks(@RequestParam String searchString) {
        ModelAndView modelAndView = new ModelAndView();
        List<Block> Blocks = BlockService.searchBlocks(searchString);
        modelAndView.addObject("Blocks", Blocks);
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("BlocksCount", Blocks.size());
        modelAndView.setViewName("Block/list");
        return modelAndView;
    }
}
