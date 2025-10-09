// 代码生成时间: 2025-10-09 21:47:45
package com.example.animation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

// 动画实体类
class Animation {
    private Long id;
    private String name;
    private String description;
    // 省略构造函数、getter和setter
}

// 动画服务接口
interface AnimationService {
    List<Animation> getAllAnimations();
    Optional<Animation> getAnimationById(Long id);
    // 省略其他方法
}

// 动画服务实现类
class AnimationServiceImpl implements AnimationService {
    // 省略依赖注入和方法实现
}

// 动画控制器
@RestController
@RequestMapping("/api/animations")
public class AnimationController {

    private final AnimationService animationService;

    @Autowired
    public AnimationController(AnimationService animationService) {
        this.animationService = animationService;
    }

    @GetMapping
    public List<Animation> getAllAnimations() {
        return animationService.getAllAnimations();
    }

    @GetMapping("/{id}")
    public Optional<Animation> getAnimationById(@PathVariable Long id) {
        return animationService.getAnimationById(id);
    }

    // 省略其他端点和错误处理
}
