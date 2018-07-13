package com.bupt.weibo.controller;

import com.bupt.weibo.dto.PermissionDto;
import com.bupt.weibo.dto.RoleDTO;
import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.dto.mapper.PermissionMapper;
import com.bupt.weibo.dto.mapper.RoleMapper;
import com.bupt.weibo.dto.mapper.UserMapper;
import com.bupt.weibo.entity.Permission;
import com.bupt.weibo.entity.Role;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.service.PermissionService;
import com.bupt.weibo.service.RoleService;
import com.bupt.weibo.service.UserService;
import com.bupt.weibo.utils.ApplicationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @anthor tanshangou
 * @time 2018/7/13
 * @description
 */
@RequestMapping(value = AdminController.PATH,produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class AdminController {
    public static final String PATH = "admin";

    public static final String SUBPATH_ROLE = "/roles";
    public static final String SUBPATH_USER = "/users";
    public static final String SUBPATH_PERMISSION = "/permissions";

    public static final String PATHVARIABLE_ID = "/{id}";

    public static final String USER_ID = "userId";

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = SUBPATH_PERMISSION)
    public ResponseEntity<Permission> addPermission(@RequestBody PermissionDto permissionDto,
                                                    UriComponentsBuilder uriComponentsBuilder){
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH + SUBPATH_PERMISSION);
        permissionDto.setResource("");
        String name = permissionDto.getResourceType() + ":" + permissionDto.getAction();
        permissionDto.setName(name);

        Permission permission = permissionMapper.convertToEntity(permissionDto);
        permissionService.addPermission(permission);
        return new ResponseEntity<Permission>(headers, HttpStatus.OK);
    }

    @GetMapping(value = SUBPATH_PERMISSION)
    public ResponseEntity<List<PermissionDto>> listPermissions(UriComponentsBuilder uriComponentsBuilder){
        HttpHeaders headers = ApplicationUtils
                .getHttpHeaders(uriComponentsBuilder,PATH + SUBPATH_PERMISSION);
        List<Permission> permissions = permissionService.listPermissions();
        List<PermissionDto> permissionDtos = permissionMapper.conovertToListDto(permissions);
        return new ResponseEntity<List<PermissionDto>>(permissionDtos,headers,HttpStatus.OK);
    }

    @GetMapping(value = SUBPATH_PERMISSION,
            params = USER_ID)
    public ResponseEntity<List<PermissionDto>> getRolesByPermissionId(UriComponentsBuilder uriComponentsBuilder,
                                                                      @RequestParam String userId){
        HttpHeaders headers = ApplicationUtils
                .getHttpHeaders(uriComponentsBuilder,PATH + SUBPATH_PERMISSION);
        List<Permission> permissions = permissionService.getPermissionsByUserId(userId);
        List<PermissionDto> permissionDtos = permissionMapper.conovertToListDto(permissions);

        return new ResponseEntity<List<PermissionDto>>(permissionDtos,headers,HttpStatus.OK);
    }


    @DeleteMapping(value = SUBPATH_PERMISSION + PATHVARIABLE_ID)
    public ResponseEntity<Long> deletePermissionById(UriComponentsBuilder uriComponentsBuilder,
                                                     @PathVariable long id){
        HttpHeaders headers = ApplicationUtils
                .getHttpHeaders(uriComponentsBuilder,PATH + SUBPATH_PERMISSION + "/" + id);
        permissionService.delPermissionById(id);
        return new ResponseEntity<Long>(id,headers,HttpStatus.OK);
    }

    @PostMapping(value = SUBPATH_ROLE)
    public ResponseEntity<RoleDTO> addRole(@RequestBody RoleDTO roleDto,
                                           UriComponentsBuilder uriComponentsBuilder){
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH + SUBPATH_ROLE);
        Role role = roleMapper.convertToEntity(roleDto);
        roleService.addRole(role);
        return new ResponseEntity<RoleDTO>(headers,HttpStatus.OK);
    }

    @GetMapping(value = SUBPATH_ROLE)
    public ResponseEntity<List<RoleDTO>> listRoles(UriComponentsBuilder uriComponentsBuilder){
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH + SUBPATH_ROLE);
        List<Role> roles = roleService.listRoles();
        List<RoleDTO> roleDtos = roleMapper.conovertToListDto(roles);
        return new ResponseEntity<List<RoleDTO>>(roleDtos,headers,HttpStatus.OK);
    }

    @GetMapping(value = SUBPATH_ROLE,
            params = USER_ID)
    public ResponseEntity<List<RoleDTO>> getRolesByUserId(UriComponentsBuilder uriComponentsBuilder,
                                                          @RequestParam String userId){
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH + SUBPATH_ROLE);
        List<Role> roles = roleService.getRolesByUserId(userId);
        List<RoleDTO> roleDtos = roleMapper.conovertToListDto(roles);
        return new ResponseEntity<List<RoleDTO>>(roleDtos,headers,HttpStatus.OK);
    }

    @PutMapping(value = SUBPATH_ROLE + PATHVARIABLE_ID + SUBPATH_PERMISSION)
    public ResponseEntity<RoleDTO> updatePermissionsById(@PathVariable long id,@RequestBody List<PermissionDto> permissionDtos,
                                                         UriComponentsBuilder uriComponentsBuilder){
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH + SUBPATH_ROLE + PATHVARIABLE_ID + SUBPATH_PERMISSION);
        List<Permission> permissions = permissionMapper.convertToListEntity(permissionDtos);
        Role role = roleService.updatePermissionsById(id,permissions);
        RoleDTO roleDto = roleMapper.convertToDto(role);
        return new ResponseEntity<RoleDTO>(roleDto,headers,HttpStatus.OK);
    }

    @DeleteMapping(value = SUBPATH_ROLE + PATHVARIABLE_ID)
    public ResponseEntity<Long> deleteRoleById(UriComponentsBuilder uriComponentsBuilder,
                                               @PathVariable long id){
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH + SUBPATH_ROLE + "/" + id);
        roleService.delRoleById(id);
        return new ResponseEntity<Long>(id,headers,HttpStatus.OK);
    }


    @PostMapping(value = SUBPATH_USER)
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDto,
                                           UriComponentsBuilder uriComponentsBuilder){
        HttpHeaders headers = ApplicationUtils
                .getHttpHeaders(uriComponentsBuilder,PATH + SUBPATH_USER);
        //User user = convertToEntity(userDTO);
        //TODO 需要修改后台添加用户逻辑
        userService.registerUser(userDto);
        return new ResponseEntity<UserDTO>(headers,HttpStatus.OK);
    }

    @GetMapping(value = SUBPATH_USER)
    public  ResponseEntity<List<UserDTO>> listUsers(UriComponentsBuilder uriComponentsBuilder){
        HttpHeaders headers = ApplicationUtils
                .getHttpHeaders(uriComponentsBuilder,PATH + SUBPATH_USER);
        List<User> users = userService.listUsers();
        List<UserDTO> userDtos = userMapper.conovertToListDto(users);
        return new ResponseEntity<List<UserDTO>>(userDtos,headers,HttpStatus.OK);
    }

    @PutMapping(value = SUBPATH_USER + PATHVARIABLE_ID + SUBPATH_ROLE)
    public ResponseEntity<UserDTO> updateRolesById(@PathVariable String id, @RequestBody List<RoleDTO> roleDtos,
                                                   UriComponentsBuilder uriComponentsBuilder){
        HttpHeaders headers = ApplicationUtils
                .getHttpHeaders(uriComponentsBuilder,SUBPATH_USER + "/" + id.toString() + SUBPATH_ROLE);
        List<Role> roles = roleMapper.convertToListEntity(roleDtos);
        User user = userService.updateRolesById(id,roles);
        UserDTO userDto = userMapper.convertToDto(user);
        return new ResponseEntity<UserDTO>(userDto,headers,HttpStatus.OK);
    }

    @PutMapping(value = SUBPATH_USER + PATHVARIABLE_ID + SUBPATH_PERMISSION)
    public ResponseEntity<UserDTO> updatePermissionsById(@PathVariable String id, @RequestBody List<PermissionDto> permissionDtos,
                                                         UriComponentsBuilder uriComponentsBuilder){
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,SUBPATH_USER + "/" + id.toString() + SUBPATH_PERMISSION);
        List<Permission> permissions = permissionMapper.convertToListEntity(permissionDtos);
        User user = userService.updatePermissionsById(id,permissions);
        UserDTO userDto = userMapper.convertToDto(user);
        return new ResponseEntity<UserDTO>(userDto,headers,HttpStatus.OK);
    }


    @DeleteMapping(value = SUBPATH_USER + PATHVARIABLE_ID)
    public ResponseEntity<String> deleteUserById(UriComponentsBuilder uriComponentsBuilder,
                                               @PathVariable String id){
        HttpHeaders headers = ApplicationUtils.getHttpHeaders(uriComponentsBuilder,PATH + SUBPATH_USER + "/" + id);
        userService.delUserById(id);
        return new ResponseEntity<String>(id,headers,HttpStatus.OK);
    }


}
