import React from 'react';
import {Button, Text} from '@mantine/core';
import styles from './header.module.css'
import {HeaderAvatar} from "@/components/header/header.avatar";
import {HeaderSearch} from "@/components/header/search/header.search";

export function HeaderMenu() {


    return (
        <div className={styles.header}>
            <div style={{display: 'flex', alignItems: 'center'}}>
                <Text style={{marginRight: '10px', fontWeight: 'bold', fontSize: '20px', color: '#fff'}}>Link 1</Text>
                <Text style={{fontWeight: 'bold', fontSize: '20px', color: '#fff'}}>Link 2</Text>
            </div>
            <div style={{display: 'flex', alignItems: 'center'}}>
                <HeaderSearch/>
                <Button variant="outline" color="white" style={{
                    borderRadius: '20px',
                    fontWeight: 'bold',
                    fontSize: '18px',
                }}>Search</Button>
            </div>
            <div style={{display: 'flex', alignItems: 'center'}}>
                <HeaderAvatar/>
            </div>
        </div>
    );
}
